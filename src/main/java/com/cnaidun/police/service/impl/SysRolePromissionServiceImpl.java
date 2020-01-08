package com.cnaidun.police.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cnaidun.police.entity.SysRolePromission;
import com.cnaidun.police.mapper.SysRolePromissionMapper;
import com.cnaidun.police.service.SysRolePromissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName SysRolePromissionServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2019/12/4 15:16
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class,readOnly = true)
@Slf4j
public class SysRolePromissionServiceImpl extends ServiceImpl<SysRolePromissionMapper, SysRolePromission> implements SysRolePromissionService {
}
