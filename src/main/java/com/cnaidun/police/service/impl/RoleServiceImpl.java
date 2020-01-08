/**
 * JFramework Generated
 */
package com.cnaidun.police.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cnaidun.police.config.mybatis.BeanUtil;
import com.cnaidun.police.config.mybatis.PageRequest;
import com.cnaidun.police.config.mybatis.PagedResult;
import com.cnaidun.police.config.tip.ErrorTip;
import com.cnaidun.police.config.tip.SuccessTip;
import com.cnaidun.police.config.tip.Tip;
import com.cnaidun.police.dto.AddRoleRequestDTO;
import com.cnaidun.police.dto.RoleListResponseDTO;
import com.cnaidun.police.dto.SysRolePromissionDto;
import com.cnaidun.police.entity.RolePermission;
import com.cnaidun.police.entity.SysRole;
import com.cnaidun.police.entity.SysUser;
import com.cnaidun.police.entity.UserRole;
import com.cnaidun.police.mapper.*;
import com.cnaidun.police.service.RoleService;
import com.cnaidun.police.vo.SysRoleVo;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author dongyin
 * @version 2019-05-29
 */
@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;


    @Override
    public PagedResult<SysRoleVo> allRolesList(int pageNo, int pageSize) {
        PageRequest pageRequest = new PageRequest(pageNo, pageSize, true);
        List<SysRoleVo> sysRoleVos = roleMapper.allRolesList(pageRequest);
        return BeanUtil.toPagedResult(sysRoleVos, pageRequest);
    }

    @Override
    public Tip queryUsersByRole(Integer roleId) {
        return new SuccessTip(roleMapper.queryUsersByRole(roleId));
    }

    @Transactional
    @Override
    public Tip bindingUserByRole(Integer roleId, List<Integer> userIds) {
        try {
            bindUserRole(roleId, userIds);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ErrorTip("操作失败");
        }
        return new SuccessTip();
    }


    @Override
    public Tip addRole(AddRoleRequestDTO addRoleRequestDTO, List<Integer> permissionIds, List<Integer> halfPermissionIds) {
        try {
            if (null != addRoleRequestDTO.getId()) {
                roleMapper.updateRole(addRoleRequestDTO);
            } else {
                roleMapper.addRole(addRoleRequestDTO);
            }
            List<RolePermission> rolePermissions = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(permissionIds)) {
                rolePermissions = permissionIds.stream()
                        .map(permissionId -> RolePermission.builder().type(1).roleId(addRoleRequestDTO.getId()).permissionId(permissionId).build()).collect(Collectors.toList());
            }
            if (CollectionUtils.isNotEmpty(halfPermissionIds)) {
                List<RolePermission> collect = halfPermissionIds.stream()
                        .map(halfPermissionId -> RolePermission.builder().type(0).roleId(addRoleRequestDTO.getId()).permissionId(halfPermissionId).build()).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(rolePermissions)) {
                    for (RolePermission rolePermission : collect) {
                        rolePermissions.add(rolePermission);
                    }
                }
            }
            if (CollectionUtils.isNotEmpty(rolePermissions)) {
                permissionMapper.deleteByRoleId(addRoleRequestDTO.getId());
                permissionMapper.insertRolePermission(rolePermissions);
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ErrorTip("操作失败");
        }
        return new SuccessTip();
    }

    @Transactional
    @Override
    public Tip ownMenu(String permissionIds) {
        List<RolePermission> rolePermissions = JSON.parseArray(permissionIds, RolePermission.class);
        try {
            if (CollectionUtils.isNotEmpty(rolePermissions)) {
                permissionMapper.deleteByRoleId(rolePermissions.get(0).getRoleId());
                permissionMapper.insertRolePermission(rolePermissions);
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ErrorTip("操作失败");
        }
        return new SuccessTip();

    }

    @Override
    public Tip relieveRole(Long Id) {
        if (null == Id) {
            return new ErrorTip("参数为空");
        }
        SysUser sysUser = new SysUser();
        sysUser.setRoleId(-1L);
        sysUserMapper.update(sysUser, new EntityWrapper<SysUser>().eq("id", Id));
        return new SuccessTip();
    }

    @Override
    public Tip addUserRole(List<String> sysUsers, Long RoleId) {
        //获取所有角色信息
        List<SysRole> sysRoles = sysRoleMapper.getAllRole();
        if (CollectionUtils.isNotEmpty(sysUsers) && null != RoleId) {
            for (String sysUser : sysUsers) {
                List<SysUser> account = sysUserMapper.findUserByAccount(sysUser);
                if (CollectionUtils.isEmpty(account)) {
                    return new ErrorTip("账号不存在，请确认账号是否正确");
                }
                Long roleId = account.get(0).getRoleId();
                String errorMessage = null;
                if (null != roleId) {
                    for (SysRole sysRole1 : sysRoles) {
                        if (roleId == sysRole1.getId()) {
                            errorMessage = "已绑定" + sysRole1.getRoleName() + "权限，请先解绑权限";
                            return new ErrorTip(errorMessage);
                        }
                    }
                    String accountNew = account.get(0).getAccount();
                    SysUser sysUser1 = new SysUser();
                    sysUser1.setRoleId(RoleId);
                    sysUserMapper.update(sysUser1, new EntityWrapper<SysUser>().eq("account", accountNew));
                }

            }
        }
        return new SuccessTip();
    }

    @Override
    public Tip updateRoleStatus(List<Long> roleId, String status) {
        if (CollectionUtils.isEmpty(roleId) || StringUtils.isEmpty(status)) {
            return new ErrorTip("参数为空");
        }
        roleId.forEach(Id -> {
            SysRole sysRole = new SysRole();
            sysRole.setStatus(status);
            sysRoleMapper.update(sysRole, new EntityWrapper<SysRole>().eq("id", Id));
        });
        return new SuccessTip();
    }

    @Transactional
    @Override
    public Tip deleteRole(Integer roleId) {
        roleMapper.deleteRole(roleId);
//        roleMapper.deleteByRoleId(roleId);
        permissionMapper.deleteByRoleId(roleId);
        return new SuccessTip();
    }

    private void bindUserRole(Integer roleId, List<Integer> userIds) {
        roleMapper.deleteByRoleId(roleId);
        if (CollectionUtils.isNotEmpty(userIds)) {
            List<UserRole> collect = userIds.stream()
                    .map(userId -> UserRole.builder().roleId(roleId).userId(userId).build()).collect(Collectors.toList());
            roleMapper.insertUserRole(collect);
        }
    }
}