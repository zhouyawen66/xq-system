package com.cnaidun.police.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName RequestInfo
 * @Description TODO
 * @Author Administrator
 * @Date 2019/12/9 14:38
 * @Version 1.0
 */
@Data
@TableName("request_info")
public class RequestInfo implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    //请求对象
    private String requestUser;
    //方法说明
    private String apiOperation;
    //请求参数
    private String requestParam;
    //返回结果
    private String responseResult;
    //ip地址
    private String ip;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


}
