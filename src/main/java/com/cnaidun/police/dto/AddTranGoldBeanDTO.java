package com.cnaidun.police.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddTranGoldBeanDTO {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 交易类型：1收入2支出
     */
    private String tranType;

    /**
     * 交易来源如：100区01幢03户
     */
    private String tranSource;

    /**
     * 交易内容如：金豆收入
     */
    private String tranContent;

    /**
     * 交易数量如：+1
     */
    private String tranNum;
}
