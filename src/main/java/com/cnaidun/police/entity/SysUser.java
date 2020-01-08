package com.cnaidun.police.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName SysUser
 * @Description TODO
 * @Author Administrator
 * @Date 2019/12/4 14:52
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "sys_user")
public class SysUser {
    /**
     * 主键id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    //账号
    private String account;
    /**
     * 用户名
     */
    private String userName;

    private String salt;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 社区区号(纯数字)
     */
    private String communityCode;

    /**
     * 社区单元名如：1区01幢01户
     */
    private String communityUnitName;

    /**
     * 邮箱
     */
    private String email;

    private String icon;

    /**
     * 手机号
     */
    private String phone;

    /**
     * openid
     */
    private String openid;

    /**
     * 状态默认-1删除1启用0禁用
     */
    private String status;

    /**
     * 积分，积分一直累计，默认0
     */
    private Long integral;

    /**
     * 金豆，季度清0，默认0
     */
    private Long goldBea;

    /**
     * 当前季度是否打分，默认0未打分 1已打分
     */
    private String scoreStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
