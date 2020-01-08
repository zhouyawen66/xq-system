package com.cnaidun.police.service;

import com.baomidou.mybatisplus.service.IService;
import com.cnaidun.police.config.mybatis.PagedResult;
import com.cnaidun.police.config.tip.Tip;
import com.cnaidun.police.entity.RequestInfo;
import com.cnaidun.police.entity.SysRolePromission;
import com.cnaidun.police.vo.RequestInfoVo;

import java.util.List;

public interface RequestInfoService  extends IService<RequestInfo> {

   PagedResult<RequestInfo> getUserLog(int pageNo, int pageSize);

   Tip deleteLog(List<Long>id);

   Tip clearLog();
}
