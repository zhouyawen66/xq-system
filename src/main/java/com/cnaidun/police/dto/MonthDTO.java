package com.cnaidun.police.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName MonthDTO
 * @Descriprion
 * @Author xuejnqiang
 * @Date 2019/12/12 14:05
 * @Version 1.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MonthDTO {

    private String name;
    private Integer data;
    private String month;
}
