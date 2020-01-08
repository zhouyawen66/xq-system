package com.cnaidun.police.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName tbScoreRecordDetail
 * @Description TODO
 * @Author Administrator
 * @Date 2019/12/4 14:41
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TbScoreRecordDetail {
    /**
     * id主键自增
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 户主姓名
     */
    private String userName;

    /**
     * 户主id
     */
    private Long userId;

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
     * 打分项id
     */
    private Long projectId;

    /**
     * 打分项目内容
     */
    private String projectContent;

    /**
     * 打分情况记录表id
     */
    private Long situationId;

    /**
     * 每一项得的积分
     */
    private Integer score;
    /**
     * description: 状态：默认1正常，2申诉中 3已处理 <br>
     */
    private char status;

    /**
     * 申诉内容
     */
    private String appealContent;

    /**
     * 创建时间
     */
    private Date createTime;


}
