package com.cnaidun.police.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cnaidun.police.entity.SysPermission;
import com.cnaidun.police.mapper.SysPermissionMapper;
import com.cnaidun.police.service.SysPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName SysPermissionServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2019/12/4 15:16
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class,readOnly = true)
@Slf4j
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {
}
