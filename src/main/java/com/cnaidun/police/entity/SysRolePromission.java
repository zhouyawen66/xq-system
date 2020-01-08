package com.cnaidun.police.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName SysRolePromission
 * @Description TODO
 * @Author zyw
 * @Date 2019/12/4 14:25
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysRolePromission {

    /**
     * 主键id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 权限id
     */
    private Long roleId;

    /**
     * 菜单id
     */
    private Long premissionId;

    private Integer type;

}
