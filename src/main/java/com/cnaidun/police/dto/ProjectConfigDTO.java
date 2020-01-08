package com.cnaidun.police.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectConfigDTO {
    private Long id;
    /**
     * 打分项目内容
     */
    @NotNull(message = "打分项目内容不能为空")
    private String projectContent;

    /**
     * 打分类别：1常规积分项；2加分项；3倡议为民”升级版”
     */
    private String scoringType;

    /**
     * 配置得分分数。10以内的正整数，常规计分、加分项必填
     */
    private Integer scoreAdd;

    /**
     * 配置扣分分数。10以内的正整数，常规计分、加分项必填
     */
    private Integer scoreSub;

    /**
     * 排序，默认0
     */
    private Integer sort;

    /**
     * 备注说明
     */
    private String remarks;
    private String status;
    private String createTime;
}
