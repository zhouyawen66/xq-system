package com.cnaidun.police.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cnaidun.police.entity.SysUser;
import org.apache.catalina.LifecycleState;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author kitty_zhu
 * @date 2019-12-04 15:19
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

   List<SysUser> findUserByAccount(String account);

}
