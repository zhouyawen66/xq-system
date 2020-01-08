package com.cnaidun.police.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cnaidun.police.entity.UserRole;
import com.cnaidun.police.mapper.UserRoleMapper;
import com.cnaidun.police.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName UserRoleServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2019/12/4 15:36
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class,readOnly = true)
@Slf4j
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
