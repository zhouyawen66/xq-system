package com.cnaidun.police.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName SysRoleVo
 * @Description TODO
 * @Author Administrator
 * @Date 2019/12/6 17:42
 * @Version 1.0
 */
@Data
public class SysRoleVo {
    /**
     * id
     */
    private int id;
    /**
     * 角色标识程序中判断使用,如"admin",这个是唯一的:
     */
    private String roleName;
    /**
     * 角色描述,UI界面显示使用
     */
    private String descr;

    private String status;
    //最后登录时间
    private String lastLoginTime;
    //最后登录ip
    private String lastIp;
    //账号
    private SysRoleVo account;



}
