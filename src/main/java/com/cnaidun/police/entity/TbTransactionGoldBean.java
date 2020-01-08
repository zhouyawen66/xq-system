package com.cnaidun.police.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName TbTransactionGoldBean
 * @Description TODO
 * @Author Administrator
 * @Date 2019/12/4 14:46
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TbTransactionGoldBean {

    /**
     * id主键自增
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 户主id
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
     * 交易数量如：1
     */
    private String tranNum;

    /**
     * 创建时间
     */
    private Date createTime;


}
