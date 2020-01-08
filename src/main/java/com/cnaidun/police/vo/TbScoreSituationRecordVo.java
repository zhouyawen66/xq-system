package com.cnaidun.police.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName TbScoreSituationRecord
 * @Description TODO
 * @Author Administrator
 * @Date 2019/12/6 9:29
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TbScoreSituationRecordVo {

    /**
     * id主键自增
     */
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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;


}
