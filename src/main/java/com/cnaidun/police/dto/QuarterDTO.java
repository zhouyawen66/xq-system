package com.cnaidun.police.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName QuarterDTO
 * @Descriprion
 * @Author xuejnqiang
 * @Date 2019/12/12 9:43
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuarterDTO {

    private String name;//分区名
    private Integer one = 0;//第一季度
    private Integer two = 0;//第二季度
    private Integer three = 0;//第三季度
    private Integer four = 0; //第四季度


}
