package com.cnaidun.police.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @ClassName HomeDTO
 * @Descriprion
 * @Author xuejnqiang
 * @Date 2019/12/12 10:57
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomeDTO {
    private String name;//分区名
    private Integer score;//分數
    private String quarter;//月份 或者 季度

    private Map<String,Integer> data;//每个月的数据

}
