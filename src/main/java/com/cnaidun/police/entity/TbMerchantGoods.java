package com.cnaidun.police.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName tb_merchant_goods
 * @Description TODO
 * @Author zyw
 * @Date 2019/12/4 14:31
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "商品类")
@TableName("tb_merchant_goods")
public class TbMerchantGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id主键自增")
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;


    @ApiModelProperty(value = "商户id")
    private Long merchantId;


    @ApiModelProperty(value = "商品id")
    private long goodsId;


    @ApiModelProperty(value = "商品名称")
    private String goodsName;


    @ApiModelProperty(value = "商品详情")
    private String goodsDetail;


    @ApiModelProperty(value = "使用金豆")
    private Long useGoldBean;


    @ApiModelProperty(value = "上下架：默认0下架1上架")
    private String status;


    @ApiModelProperty(value = "商品活动开始日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date goodsActivityStart;


    @ApiModelProperty(value = "商品活动开始日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date goodsActivityEnd;


    @ApiModelProperty(value = "商品缩略图")
    private String goodsPic;


    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;


    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;

}
