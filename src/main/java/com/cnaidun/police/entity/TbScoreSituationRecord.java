package com.cnaidun.police.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName TbScoreSituationRecord
 * @Description TODO
 * @Author Administrator
 * @Date 2019/12/4 14:44
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TbScoreSituationRecord {

    /**
     * id主键自增
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 户主姓名
     */
    private String householderName;

    /**
     * 户主id
     */
    private Long householderId;

    /**
     * 社区单元名称
     */
    private String communityUnitName;

    /**
     * 网格员id
     */
    private Long recorderId;

    /**
     * 网格员名字
     */
    private String recorderName;


    /**
     * 每一项得的积分
     */
    private Integer score;


    /**
     * 创建时间
     */
    private Date createTime;
}
