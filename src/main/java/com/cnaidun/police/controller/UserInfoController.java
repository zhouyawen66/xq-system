package com.cnaidun.police.controller;

import com.cnaidun.police.config.mybatis.PagedResult;
import com.cnaidun.police.config.tip.ErrorTip;
import com.cnaidun.police.config.tip.SuccessTip;
import com.cnaidun.police.config.tip.Tip;
import com.cnaidun.police.dto.SysUserDto;
import com.cnaidun.police.dto.UserInfoRequestDTO;
import com.cnaidun.police.entity.UserInfo;
import com.cnaidun.police.service.UserInfoService;
import com.cnaidun.police.util.AESUtil;
import com.cnaidun.police.vo.UserInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author dongyin
 */
@Api(description = "后台用户相关api")
@Slf4j
@RestController
@RequestMapping("/sysUser")
public class UserInfoController {
    @Resource
    UserInfoService userInfoService;


    @ApiOperation("添加用户")
    @PostMapping("/addUser")
//    @RequiresPermissions("user:add")
    public Tip addUser(@Valid UserInfoRequestDTO user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ErrorTip(bindingResult.getFieldError().getDefaultMessage());
        }
        //对象类型转化
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(user, userInfo);
        userInfo.setPassword(user.getPassword());
        String password = AESUtil.desEncrypt(userInfo.getPassword());
        if (null != password) {
            userInfo.setPassword(password);
        }
        return userInfoService.postUserInfo(userInfo);
    }

    @ApiOperation("删除用户")
    @PostMapping("/deleteUser")
//    @RequiresPermissions("user:delete")
    public Tip deleteUser(@RequestParam List<Long> id) {
        userInfoService.dropUserInfo(id);
        return new SuccessTip();
    }

    @ApiOperation("查询所有用户")
    @PostMapping("/queryAll")
    public Tip queryAll() {
        return new SuccessTip(userInfoService.queryAll());
    }

    @ApiOperation("用户列表")
    @PostMapping("/allUserList")
    public Tip allUserList(@RequestParam Integer pageNo, @RequestParam Integer pageSize, @RequestParam Long roleId, @RequestParam(required = false) String account) {
        PagedResult<UserInfoVo> userList = userInfoService.getUserList(pageNo, pageSize, roleId, account);
        return new SuccessTip(userList);
    }

    @ApiOperation("修改用户禁用或启用状态")
    @PostMapping("/updateUserStatus")
    public Tip updateUserStatus(@RequestParam List<Long>id ,@RequestParam String status) {
        userInfoService.updateUserStatus(id,status);
        return new SuccessTip();
    }

    @ApiOperation("获得用户计分列表")
    @PostMapping("/getIntegrate/list")
    public Tip getUserIntegrateList(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam Long id) {
        return new SuccessTip(userInfoService.getUserIntegrateList(pageNo, pageSize, id));
    }

    @ApiOperation("获得用户积分详情列表")
    @PostMapping("/getSocreInfo/list")
    public Tip getSocreInfo(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam Long id, @RequestParam Long userId) {
        return new SuccessTip(userInfoService.getSocreInfo(pageNo, pageSize, id, userId));
    }

    @ApiOperation("修改密码")
    @PostMapping("/modifyPWD")
//    @RequiresPermissions("personal:modifyPwd")
    public Tip modifyPWD(@RequestParam Integer id,
                         @RequestParam String pwd) {
        return (userInfoService.resetPasswrod(id, pwd) == -1) ?
                new ErrorTip("密码修改失败。") : new SuccessTip("密码修改成功");
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Tip login(@RequestParam String account, @RequestParam String password, String openId) {
        return userInfoService.login(account, AESUtil.desEncrypt(password), openId);
    }

    @ApiOperation("扫码登录")
    @PostMapping("/scanCodeLogin")
    public Tip scanCodeLogin(@RequestParam String code) {
        return userInfoService.scanCodeLogin(code);
    }


    @ApiOperation("用户退出")
    @PostMapping("/exit")
    public void exit() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
    }


    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        String credentials = "qwe123456";
        int hashIterations = 1024;
        String randomSixNum = "572914";
        ByteSource credentialsSalt = ByteSource.Util.bytes(randomSixNum);
        Object obj = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
        System.out.println(obj);
        System.out.println(randomSixNum);
    }
}
