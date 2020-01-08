package com.cnaidun.police.entity;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 系统角色
 * @author dongyin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysRole {
    /**
     * id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     * 角色标识程序中判断使用,如"admin",这个是唯一的:
     */
    private String roleName;
    /**
     * 角色描述,UI界面显示使用
     */
    private String descr;

    private String status;
    /**
     * 角色 -- 权限关系：多对多关系
     */
    private List<SysPermission> permissions;
    /**
     * 用户 - 角色关系定义
     */
    private List<UserInfo> userInfos;


    private Date createTime;
}