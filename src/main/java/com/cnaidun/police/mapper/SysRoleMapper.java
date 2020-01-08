package com.cnaidun.police.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cnaidun.police.entity.SysRole;
import org.apache.catalina.LifecycleState;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author kitty_zhu
 * @date 2019-12-04 15:17
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
       List<SysRole> getAllRole();
}
