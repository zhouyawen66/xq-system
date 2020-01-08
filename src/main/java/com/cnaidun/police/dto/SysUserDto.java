package com.cnaidun.police.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName SysUserDto
 * @Description TODO
 * @Author Administrator
 * @Date 2019/12/5 16:09
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysUserDto {

    /**
     * id
     */
    private Integer id;
    // 1开启 0关闭 -1删除
    private char status;
}
