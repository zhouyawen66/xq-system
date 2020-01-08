package com.cnaidun.police.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName TbScoreProjectConfig
 * @Description TODO
 * @Author zyw
 * @Date 2019/12/4 14:38
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TbScoreProjectConfig {

    /**
     * 主键id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 打分项目内容
     */
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

    /**
     * 状态：默认1正常，0禁用
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
