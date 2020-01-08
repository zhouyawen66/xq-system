package com.cnaidun.police.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName UserInfoVo
 * @Description TODO
 * @Author zyw
 * @Date 2019/12/5 13:42
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoVo  {

    private Long id;

    private String account;

    private String userName;
    //角色id
    private Long roleId;
    //社区区号
    private String communityCode;
    //社区单元名如：1区01幢01户
    private String communityUnitName;
    //邮箱
    private String email;

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

    private String roleName;

}
