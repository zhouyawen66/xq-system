/**
 * JFramework Generated
 */
package com.cnaidun.police.service.impl;


import com.cnaidun.police.config.mybatis.BeanUtil;
import com.cnaidun.police.config.mybatis.PageRequest;
import com.cnaidun.police.config.mybatis.PagedResult;
import com.cnaidun.police.config.shiro.UsernamePasswordToken;
import com.cnaidun.police.config.tip.ErrorTip;
import com.cnaidun.police.config.tip.SuccessTip;
import com.cnaidun.police.config.tip.Tip;
import com.cnaidun.police.constant.Constant;
import com.cnaidun.police.dto.MenuResponseDTO;
import com.cnaidun.police.dto.StatisticsDTO;
import com.cnaidun.police.dto.SysUserDto;
import com.cnaidun.police.dto.UserInfoRequestDTO;
import com.cnaidun.police.entity.TbMerchantInfo;
import com.cnaidun.police.entity.UserInfo;
import com.cnaidun.police.exception.ServiceException;
import com.cnaidun.police.mapper.SysUserMapper;
import com.cnaidun.police.mapper.TbMerchantInfoMapper;
import com.cnaidun.police.mapper.UserInfoMapper;
import com.cnaidun.police.service.UserInfoService;
import com.cnaidun.police.util.AESUtil;
import com.cnaidun.police.util.RandomUtil;
import com.cnaidun.police.util.TreeUtil;
import com.cnaidun.police.util.wechat.WechatUtil;
import com.cnaidun.police.util.wechat.bean.WechatUserAuthInfo;
import com.cnaidun.police.vo.TbScoreRecordDetailVo;
import com.cnaidun.police.vo.TbScoreSituationRecordVo;
import com.cnaidun.police.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author dongyin
 * @version 2019-05-23
 */
@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private TbMerchantInfoMapper tbMerchantInfoMapper;

    private static Long USER_ROLE = 2L;

    private static Long MERCHANT_ROLE = 4L;

    @Override
    public UserInfo findByAccount(String account) {
        return userInfoMapper.findByAccount(account);
    }

    /**
     * 补全用户密码以及salt
     *
     * @param userInfo
     * @return
     */
    @Override
    public UserInfo completeUserPassword(UserInfo userInfo) {
        String hashAlgorithmName = Constant.MD5;
        String credentials = userInfo.getPassword();
        int hashIterations = Constant.HASHITERATIONS;
        String randomSixNum = RandomUtil.getRandomSixNum();
        ByteSource credentialsSalt = ByteSource.Util.bytes(randomSixNum);
        Object obj = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
        userInfo.setPassword(obj.toString());
        userInfo.setSalt(randomSixNum);
        return userInfo;
    }

    @Override
    public Map completeUserPassword(Integer id, String password) {
        String hashAlgorithmName = Constant.MD5;
        String credentials = password;
        int hashIterations = Constant.HASHITERATIONS;
        //获取库中原有salt
        String salt = userInfoMapper.getSalt(id);
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
        Object obj = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
        Map map = new HashMap() {{
            put("password", obj.toString());
            put("salt", salt);
        }};
        return map;
    }

    @Override
    public List<MenuResponseDTO> findUserPermissions(String account) {
        List<MenuResponseDTO> userPermissions = userInfoMapper.findUserPermissions(account);
        return packageInfo(userPermissions);
    }

    public List<MenuResponseDTO> packageInfo(List<MenuResponseDTO> userPermissions) {
        return userPermissions.stream()
                .filter(parent -> parent.getParentId() == 0)
                .map(parent -> MenuResponseDTO.builder()
                        .id(parent.getId())
                        .parentId(parent.getParentId())
                        .label(parent.getLabel())
                        .url(parent.getUrl())
                        .type(parent.getType())
                        .icon(parent.getIcon())
                        .children(
                                userPermissions.stream()
                                        .filter(child -> child.getParentId().equals(parent.getId()))
                                        .map(child -> MenuResponseDTO.builder()
                                                .id(child.getId())
                                                .parentId(parent.getId())
                                                .label(child.getLabel())
                                                .url(child.getUrl())
                                                .type(child.getType())
                                                .icon(child.getIcon())
                                                .children(
                                                        userPermissions.stream()
                                                                .filter(button -> Constant.BUTTON.equals(button.getType()) && button.getParentId().equals(child.getId()))
                                                                .map(button -> MenuResponseDTO.builder()
                                                                        .id(button.getId())
                                                                        .parentId(child.getId())
                                                                        .label(button.getLabel())
                                                                        .url(button.getUrl())
                                                                        .type(button.getType())
                                                                        .type(button.getIcon())
                                                                        .buttonType(button.getButtonType()).build()).collect(Collectors.toList())
                                                )
                                                .build())
                                        .collect(Collectors.toList())
                        ).build()).collect(Collectors.toList());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Tip postUserInfo(UserInfo userInfo) {
        UserInfo byAccount = userInfoMapper.findByAccount(userInfo.getAccount());
        if (byAccount != null) {
            return new ErrorTip("用户名已存在");
        }
        UserInfo userInfo1 = completeUserPassword(userInfo);
        //如果不是户主不要插入以下字段
        if (StringUtils.isEmpty(userInfo1.getCommunityCode()) || StringUtils.isEmpty(userInfo1.getCommunityUnitName())) {
            userInfo1.setCommunityCode("");
            userInfo1.setCommunityUnitName("");
        }
        try {
            //新增用户信息
            Integer id = userInfoMapper.addUserInfo(userInfo1);
            //如果新增用户是商家，则插入用户信息表中
            if (MERCHANT_ROLE == userInfo.getRoleId() && 1 == id) {
                Integer id1 = userInfoMapper.findByAccount(userInfo.getAccount()).getId();
                TbMerchantInfo tbMerchantInfo = new TbMerchantInfo();
                tbMerchantInfo.setUserId(Long.parseLong(id1.toString()));
                tbMerchantInfoMapper.insert(tbMerchantInfo);
            }
            return new SuccessTip("成功", id);
        } catch (Exception e) {
            log.info("postUserInfo=====>>> 用户数据新增失败 param:{}", userInfo1, e);
            return new ErrorTip("失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void dropUserInfo(List<Long> id) {
        if (CollectionUtils.isNotEmpty(id)) {
            id.forEach(d -> {
                userInfoMapper.dropUserInfo(d);
            });
        }
        return;
    }

    @Override
    public List<Map<String, Object>> queryAll() {
        return userInfoMapper.queryAll();
    }

//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public Integer updatePWD(Integer id, String oldPWD, String newPWD) {
//        Map m = completeUserPassword(id, oldPWD);
//        if (userInfoMapper.isPasswordRight(m)) {
//            return userInfoMapper.updatePWD(completeUserPassword(UserInfo.builder()
//                    .id(id)
//                    .password(newPWD)
//                    .build()));
//        } else {
//            return -1;
//        }
//    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer resetPasswrod(Integer id, String pwd) {
        try {
            if (StringUtils.isNotBlank(pwd) && null != id) {
                String password = AESUtil.desEncrypt(pwd);
                if (null == password) {
                    password = pwd;
                }
                UserInfo userInfo = new UserInfo();
                userInfo.setPassword(password);
                userInfo.setId(1);
                Integer integer = userInfoMapper.updatePWD(completeUserPassword(userInfo));
                return integer;
            }
        } catch (Exception e) {
            log.info("resetPasswrod===>>>重置密码异常 param:{}", pwd, e);
        }
        return -1;
    }

    /**
     * description: RoleId=2为户主列表  如果不等2则为管理员列表 <br>
     * version: 1.0 <br>
     * date: 2019/12/5 14:24 <br>
     * author: zhouyw <br>
     * <p>
     * [pageNo, pageSize, RoleId]
     *
     * @return com.cnaidun.police.config.mybatis.PagedResult<com.cnaidun.police.vo.UserInfoVo>
     */
    @Override
    public PagedResult<UserInfoVo> getUserList(int pageNo, int pageSize, Long RoleId, String account) {
        PageRequest pageRequest = new PageRequest(pageNo, pageSize, true);
        // 查看所有户主的列表
        try {
            if (null != RoleId && USER_ROLE == RoleId) {
                List<UserInfoVo> householdList = userInfoMapper.getHouseholdList(pageRequest, account);
                return BeanUtil.toPagedResult(householdList, pageRequest);
            }
        } catch (Exception e) {
            log.info("getUserList=====>>> 查询户主列表数据失败 param:{}", RoleId, e);
        }
        try {
            //查看管理员列表
            List<UserInfoVo> adminList = userInfoMapper.getAdminList(pageRequest, account);
            return BeanUtil.toPagedResult(adminList, pageRequest);
        } catch (Exception e) {
            log.info("getUserList=====>>> 查询管理员列表数据失败 param:{}", RoleId, e);
        }
        return null;
    }

    public PagedResult<TbScoreSituationRecordVo> getUserIntegrateList(int pageNo, int pageSize, Long id) {
        PageRequest pageRequest = new PageRequest(pageNo, pageSize, true);
        List<TbScoreSituationRecordVo> vos = new ArrayList<>();
        try {
            if (null != id) {
                vos = userInfoMapper.getUserIntegrateScoreList(pageRequest,id);
            }
        } catch (Exception e) {
            log.info("getUserIntegrateList=====>>> 查询户主计分列表失败 param:{}", id, e);
        }
        return BeanUtil.toPagedResult(vos, pageRequest);

    }

    public PagedResult<TbScoreRecordDetailVo> getSocreInfo(int pageNo, int pageSize, Long id, Long userId) {
        PageRequest pageRequest = new PageRequest(pageNo, pageSize, true);
        List<TbScoreRecordDetailVo> vos = new ArrayList<>();
        try {
            if (null != id && null != userId) {
                vos = userInfoMapper.getUserScoreList(pageRequest,id, userId);
            }
        } catch (Exception e) {
            log.info("getSocreInfo=====>>> 获取用户打分明细失败 param:{}", id, e);
        }
        return BeanUtil.toPagedResult(vos, pageRequest);
    }

    /**
     * description: status 1启用 0禁用 <br>
     * version: 1.0 <br>
     * date: 2019/12/5 15:53 <br>
     * author: zhouyw <br>
     * <p>
     * [id, status]
     *
     * @return java.lang.Integer
     */
    @Override
    public void updateUserStatus(List<Long> Id, String status) {
        if (CollectionUtils.isNotEmpty(Id) && StringUtils.isNotBlank(status)) {
            try {
                Id.forEach(id -> {
                    userInfoMapper.updateUserStatus(id, status);
                });

            } catch (Exception e) {
                log.info("updateUserStatus ====>>>修改异常 param:{}", status, e);
            }
        }
    }

    @Override
    public Tip scanCodeLogin(String code) {
        WechatUserAuthInfo userAuthInfo = WechatUtil.getAccessToken(code);
        if (userAuthInfo == null || StringUtils.isBlank(userAuthInfo.getOpenid())) {
            return new ErrorTip("扫码登录失败");
        }
        String openid = userAuthInfo.getOpenid();
        String account = userInfoMapper.findAccountByOpenId(openid);
        if (StringUtils.isBlank(account)) {
            //未绑定账号
            return new ErrorTip(201, "请先绑定账号", openid);
        }
        //已绑定账号直接登录
        UsernamePasswordToken token = new UsernamePasswordToken(account);
        return loginReturnData(token, null);
    }

    @Override
    public Tip login(String account, String password, String openId) {
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        try {
            return loginReturnData(token, openId);
        } catch (AuthenticationException e) {
            String exception = e.getClass().getName();
            String msg;
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "账号不存在";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "密码不正确";
            } else {
                msg = "登录失败";
                System.out.println("else -- >" + exception);
            }
            return new ErrorTip(msg);
        }
    }
//	@Override
//	public Tip login(String account, String password, String openId) {
//		UsernamePasswordToken token = new UsernamePasswordToken(account, password);
//		try {
//			return loginReturnData(token, openId);
//		} catch (AuthenticationException e) {
//			String exception = e.getClass().getName();
//			String msg;
//			if (UnknownAccountException.class.getName().equals(exception)) {
//				System.out.println("UnknownAccountException -- > 账号不存在：");
//				msg = "账号不存在";
//			} else if (IncorrectCredentialsException.class.getName().equals(exception)) {
//				System.out.println("IncorrectCredentialsException -- > 密码不正确：");
//				msg = "密码不正确";
//			} else {
//				msg = "登录失败";
//				System.out.println("else -- >" + exception);
//			}
//			return new ErrorTip(msg);
//		}
//	}

    /**
     * 数据分析模块（饼图）
     *
     * @return
     */
    @Override
    public Tip findUserCountByArea() {
        return new SuccessTip(userInfoMapper.selectUserCountByArea());
    }

    private Tip loginReturnData(UsernamePasswordToken token, String openId) {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.login(token);
        Session session = SecurityUtils.getSubject().getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(Constant.SESSION_USER_INFO);
        List<MenuResponseDTO> userPermissions = findUserPermissions(userInfo.getAccount());
        Map map = new HashMap(3);
        map.put("userPermissions", userPermissions);
        map.put("JSESSIONID", session.getId().toString());
        map.put(Constant.SESSION_USER_INFO, userInfo);

        if (StringUtils.isNotBlank(openId)) {
            //如果openid不为null，则是用户扫码后，绑定账号
            Map pa = new HashMap(2);
            pa.put("openId", openId);
            pa.put("account", userInfo.getAccount());
            userInfoMapper.updateOpenIdByAccount(pa);
        }
        return new SuccessTip(map);
    }

    /**
     *区季度分值分析图
     */

}