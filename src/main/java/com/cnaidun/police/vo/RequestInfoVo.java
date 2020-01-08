package com.cnaidun.police.vo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

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
public class RequestInfoVo implements Serializable {

    private Long id;
    //请求用户
    private String requestUser;
    //方法说明
    private String apiOperation;
    //请求参数
    private String requestParam;
    //返回结果
    private String responseResult;
    //ip地址
    private String ip;
    //创建时间
    private String createTime;


}
