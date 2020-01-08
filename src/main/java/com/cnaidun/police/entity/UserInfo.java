package com.cnaidun.police.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息
 *
 * @author dongyin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo implements Serializable {
    /**
     * id
     */
    private Integer id;
    /**
     * 帐号
     */
    private String account;
    /**
     * 名称（昵称或者真实姓名，不同系统不同定义）
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 加密密码的盐
     */
    private String salt;

    //角色id
    private Long roleId;

    private String email;

    //社区区号
    private String communityCode;
    //社区单元名如：1区01幢01户
    private String communityUnitName;

    //邮箱
    /**
     * 用户状态,0删除用户  1正常用户
     */
    private String state;

    private char status;
    /**
     * 一个用户的不同权限
     */
    private List<SysPermission> permissions;

}