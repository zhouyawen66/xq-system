package com.cnaidun.police.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cnaidun.police.entity.RequestInfo;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

@Mapper
public interface RequestInfoMapper extends BaseMapper<RequestInfo> {
   Integer clearLog();
}
