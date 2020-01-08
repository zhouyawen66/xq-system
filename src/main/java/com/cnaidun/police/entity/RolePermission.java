package com.cnaidun.police.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 角色权限关系表
 * @author dongyin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolePermission{

    private Integer roleId;
    private Integer permissionId;
    /**
     * 全选1 半选0
     */
    private Integer type;
}