package com.cnaidun.police.service;


import com.cnaidun.police.config.mybatis.PagedResult;
import com.cnaidun.police.config.tip.Tip;
import com.cnaidun.police.dto.MenuResponseDTO;
import com.cnaidun.police.dto.SysUserDto;
import com.cnaidun.police.dto.UserInfoRequestDTO;
import com.cnaidun.police.entity.UserInfo;
import com.cnaidun.police.vo.TbScoreRecordDetailVo;
import com.cnaidun.police.vo.TbScoreSituationRecordVo;
import com.cnaidun.police.vo.UserInfoVo;

import java.util.List;
import java.util.Map;

/**
 * 用户信息服务层
 *
 * @author dongyin
 * @version 2019-05-23
 */
public interface UserInfoService {

    UserInfo findByAccount(String account);

    UserInfo completeUserPassword(UserInfo userInfo);

    Map completeUserPassword(Integer id, String password);

    List<MenuResponseDTO> findUserPermissions(String account);

    Tip postUserInfo(UserInfo userInfo);

    void dropUserInfo(List<Long> id);

    List<Map<String, Object>> queryAll();

//    Integer updatePWD(Integer id, String oldPWD, String newPWD);

    Integer resetPasswrod(Integer id,String pwd);

//    PagedResult<UserInfoRequestDTO> allUserList(int pageNo, int pageSize);

    PagedResult<UserInfoVo> getUserList(int pageNo, int pageSize, Long roleId,String account );

    PagedResult<TbScoreSituationRecordVo> getUserIntegrateList(int pageNo, int pageSize, Long id);

    PagedResult<TbScoreRecordDetailVo> getSocreInfo(int pageNo, int pageSize, Long id, Long userId);

    void updateUserStatus(List<Long> Id,String status);

    Tip scanCodeLogin(String code);

    Tip login(String account, String password, String openId);

    Tip findUserCountByArea();

    //
}