package com.cnaidun.police.dto;

import lombok.Data;

/**
 * @ClassName SysRolePromissionDto
 * @Description TODO
 * @Author Administrator
 * @Date 2019/12/9 11:20
 * @Version 1.0
 */
@Data
public class SysRolePromissionDto {

    private Long roleId;

    /**
     * 菜单id
     */
    private Long premissionId;

    //全选1  半选0
    private Integer type;
}
