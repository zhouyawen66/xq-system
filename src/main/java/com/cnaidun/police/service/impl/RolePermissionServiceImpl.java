package com.cnaidun.police.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cnaidun.police.entity.RolePermission;
import com.cnaidun.police.mapper.RolePermissionMapper;
import com.cnaidun.police.service.RolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName RolePermissionServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2019/12/4 15:10
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class,readOnly = true)
@Slf4j
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Autowired
    RolePermissionMapper rolePermissionMapper;


    @Override
    public void InsertRow(RolePermission rolePermission) {

    }
}
