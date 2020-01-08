package com.cnaidun.police.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 首页统计用DTO
 *
 * @ClassName StatisticsDTO
 * @Descriprion
 * @Author xuejnqiang
 * @Date 2019/12/5 10:06
 * @Version 1.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticsDTO {
    /**
     * 统计数量
     */
    private Integer value;
    /**
     * 统计名称
     */
    private String name;

}
