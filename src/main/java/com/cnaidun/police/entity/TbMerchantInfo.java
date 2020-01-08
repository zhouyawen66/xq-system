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
 * @ClassName TbMerchantInfo
 * @Description TODO
 * @Author zyw
 * @Date 2019/12/4 14:34
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "商户信息类")
@TableName("tb_merchant_info")
public class TbMerchantInfo implements Serializable {

    @ApiModelProperty(value = "id主键自增")
    @TableId(type=IdType.AUTO)
    private Long id;


    @ApiModelProperty(value = "户主姓名")
    private String userName;


    @ApiModelProperty(value = "户主id")
    private Long userId;


    @ApiModelProperty(value = "商铺名称")
    private String merchantName;


    @ApiModelProperty(value = "商铺地址")
    private String merchantAddress;


    @ApiModelProperty(value = "商铺电话")
    private String merchantTel;


    @ApiModelProperty(value = "是否营业：默认0待业，1营业")
    private String status;


    @ApiModelProperty(value = "是否完善，默认0未完善 1已完善")
    private String perfectStatus;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @ApiModelProperty(value = "营业时间起")
    private String doBusinessStart;

    /**
     * 营业时间止
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @ApiModelProperty(value = "户主姓名")
    private String doBusinessEnd;


    @ApiModelProperty(value = "备注")
    private String remarks;


    @ApiModelProperty(value = "店铺缩略图")
    private String merchantPic;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @ApiModelProperty(value = "最后登录时间")
    private Date lastLoginTime;

    @ApiModelProperty(value = "最后登录ip")
    private String lastLoginIp;


}
