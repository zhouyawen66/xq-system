package com.cnaidun.police.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author kitty_zhu
 * @date 2019-12-05 14:24
 * 与前端交互的护数据
 */
@Data
@ApiModel(value = "商品信息类")
public class TbMerchantGoodsVo {

    @ApiModelProperty(value = "id主键自增")
    private Long id;


    @ApiModelProperty(value = "商户id")
    private Long merchantId;


    @ApiModelProperty(value = "商品id")
    private long goodsId;


    @ApiModelProperty(value = "商品名称")
    private String goodsName;


    @ApiModelProperty(value = "商铺详情")
    private String goodsDetail;


    @ApiModelProperty(value = "使用金豆")
    private Long useGoldBean;


    @ApiModelProperty(value = "上下架：默认0下架1上架")
    private String status;


    @ApiModelProperty(value = "商品活动开始日期")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private String goodsActivityStart;


    @ApiModelProperty(value = "商品活动开始日期")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private String goodsActivityEnd;


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
