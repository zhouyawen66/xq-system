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
 * @ClassName TbMessage
 * @Description TODO
 * @Author Administrator
 * @Date 2019/12/4 14:36
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("tb_message")
@ApiModel(value = "消息列表类")
public class TbMessage implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    @ApiModelProperty(value = "id主键自增")
    private Long id;


    @ApiModelProperty(value = "户主id")
    private Long userId;


    @ApiModelProperty(value = "消息状态，默认1正常，0删除")
    private String status;


    @ApiModelProperty(value = "消息内容")
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "发送人（推送人）")
    private String sender;

    @ApiModelProperty(value = "接收（被推送人）")
    private String recipient;
    //消息类型：1 社区消息 2我的消息
    private String msgType;

}
