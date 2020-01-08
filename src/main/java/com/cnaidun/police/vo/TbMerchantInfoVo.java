package com.cnaidun.police.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author kitty_zhu
 * @date 2019-12-05 09:33
 * 与前端交互的数据
 */
@Data
@ApiModel(value = "商户类")
public class TbMerchantInfoVo implements Serializable {

    @ApiModelProperty(value = " id主键自增")
    private Long id;

    @ApiModelProperty(value = " 户主姓名")
    private String userName;

    @ApiModelProperty(value = " 户主id")
    private Long userId;

    @ApiModelProperty(value = " 商铺名称")
    private String merchantName;

    @ApiModelProperty(value = " 商铺地址")
    private String merchantAddress;

    @ApiModelProperty(value = " 商铺电话")
    private String merchantTel;

    @ApiModelProperty(value = " 是否营业：默认0待业，1营业")
    private String status;

    @ApiModelProperty(value = " 是否完善，默认0未完善 1已完善")
    private String perfectStatus;

    @ApiModelProperty(value = " 营业时间起")
    private String doBusinessStart;

    @ApiModelProperty(value = " 营业时间止")
    private String doBusinessEnd;

    @ApiModelProperty(value = " 备注")
    private String remarks;

    @ApiModelProperty(value = " 店铺缩略图")
    private String merchantPic;

    @ApiModelProperty(value = " 最后登录时间")
    private Date lastLoginTime;

    @ApiModelProperty(value = " 最后登录ip")
    private String lastLoginIp;
}
