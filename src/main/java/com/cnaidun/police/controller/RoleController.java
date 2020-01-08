package com.cnaidun.police.controller;

import com.cnaidun.police.config.mybatis.PagedResult;
import com.cnaidun.police.config.tip.ErrorTip;
import com.cnaidun.police.config.tip.SuccessTip;
import com.cnaidun.police.config.tip.Tip;
import com.cnaidun.police.dto.AddRoleRequestDTO;
import com.cnaidun.police.dto.RoleListResponseDTO;
import com.cnaidun.police.entity.RolePermission;
import com.cnaidun.police.entity.SysUser;
import com.cnaidun.police.interceptor.ratelimit.RateLimit;
import com.cnaidun.police.service.RoleService;
import com.cnaidun.police.vo.SysRoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author dongyin
 */
@Api(description = "后台角色相关api")
@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    RoleService roleService;

    @ApiOperation("角色列表")
    @PostMapping("/allRoles")
    public Tip allRoles(@RequestParam Integer pageNo, @RequestParam Integer pageSize) {
        return new SuccessTip(roleService.allRolesList(pageNo, pageSize));
    }


    @ApiOperation("通过角色绑定用户")
    @PostMapping("/bindingUserByRole")
    @RateLimit(lockName = "addRole", keys = "roleId")
//    @RequiresPermissions("role:bindUser")
    public Tip bindingUserByRole(@RequestParam Integer roleId,
                                 @RequestParam(value = "userIds") List<Integer> userIds) {
        return roleService.bindingUserByRole(roleId, userIds);
    }

//
    @ApiOperation("通过角色查询用户")
    @PostMapping("/queryUsersByRole")
    public Tip queryUsersByRole(@RequestParam Integer roleId) {
        return roleService.queryUsersByRole(roleId);
    }

    @ApiOperation("解除授权")
    @PostMapping("/relieveRole")
    public Tip relieveRole(@RequestParam Long id) {
        return roleService.relieveRole(id);
    }

    @ApiOperation("添加用户权限")
    @PostMapping("//addUserRole")
    public Tip addUserRole(@RequestParam List<String> sysUserList,@RequestParam Long roleId) {
        return roleService.addUserRole(sysUserList,roleId);
    }

    @ApiOperation("启用、禁用角色")
    @PostMapping("/updateRoleStatus")
    public Tip updateRoleStatus(@RequestParam List<Long>roleId,@RequestParam String status) {
        return roleService.updateRoleStatus(roleId,status);
    }

//    @ApiOperation("添加、修改角色")
//    @PostMapping("/addRole")
////    @RequiresPermissions("role:addupdate")
////    @RateLimit(lockName = "addRole",keys = "addRoleRequestDTO.role")
//    //, BindingResult bindingResult
//    public Tip addRole(AddRoleRequestDTO addRoleRequestDTO,
//                       @RequestParam(required = false) List<Integer> permissionIds,
//                       @RequestParam(required = false) List<Integer> halfPermissionIds) {
////        if (bindingResult.hasErrors()) {
////            return new ErrorTip(bindingResult.getFieldError().getDefaultMessage());
////        }
//        return roleService.addRole(addRoleRequestDTO, permissionIds, halfPermissionIds);
//    }

    @ApiOperation("拥有权限菜单")
    @PostMapping("/ownerMenu")
    public Tip ownMenu(@RequestParam String permissionIds){
        return roleService.ownMenu(permissionIds);
    }



    @ApiOperation("删除角色")
    @DeleteMapping("/deleteRole")
//    @RequiresPermissions("role:delete")
    @RateLimit(lockName = "deleteRole", keys = "roleId")
    public Tip deleteRole(@RequestParam Integer roleId) {
        return roleService.deleteRole(roleId);
    }

//    @ApiOperation("通过角色查询用户")
//    @PostMapping("/queryUsersByRole")
//    public Tip queryUsersByRole(@RequestParam Integer roleId){
//        return roleService.queryUsersByRole(roleId);
//    }

    @ApiOperation("添加、修改角色")
    @PostMapping("/addRole")
//    @RequiresPermissions("role:addupdate")
    @RateLimit(lockName = "addRole",keys = "addRoleRequestDTO.role")
    public Tip addRole(@Valid AddRoleRequestDTO addRoleRequestDTO, BindingResult bindingResult,
                       @RequestParam(value = "permissionIds") List<Integer> permissionIds,
                       @RequestParam(value = "halfPermissionIds") List<Integer> halfPermissionIds) {
        if (bindingResult.hasErrors()) {
            return new ErrorTip(bindingResult.getFieldError().getDefaultMessage());
        }
        return roleService.addRole(addRoleRequestDTO, permissionIds, halfPermissionIds);
    }
}
