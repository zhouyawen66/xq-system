package com.cnaidun.police.service;


import com.cnaidun.police.config.mybatis.PagedResult;
import com.cnaidun.police.config.tip.Tip;
import com.cnaidun.police.dto.AddRoleRequestDTO;
import com.cnaidun.police.dto.RoleListResponseDTO;
import com.cnaidun.police.dto.SysRolePromissionDto;
import com.cnaidun.police.entity.RolePermission;
import com.cnaidun.police.entity.SysUser;
import com.cnaidun.police.vo.SysRoleVo;
import io.swagger.models.auth.In;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

/**
 * 角色服务层
 * @author dongyin
 * @version 2019-05-29
 */
public interface RoleService {

    PagedResult<SysRoleVo> allRolesList(int pageNo, int pageSize);

    Tip queryUsersByRole(Integer roleId);

    Tip bindingUserByRole(Integer roleId, List<Integer> userIds);

    Tip addRole(AddRoleRequestDTO addRoleRequestDTO, List<Integer> userIds, List<Integer> halfPermissionIds);

    Tip ownMenu(String permissionIds);

    Tip relieveRole(Long Id);

    Tip addUserRole(List<String> sysUserList,Long RoleId);

    Tip updateRoleStatus(List<Long>roleId,String status);

    Tip deleteRole(Integer roleId);









}