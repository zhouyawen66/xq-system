package com.cnaidun.police.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cnaidun.police.config.mybatis.BeanUtil;
import com.cnaidun.police.config.mybatis.PageRequest;
import com.cnaidun.police.config.mybatis.PagedResult;
import com.cnaidun.police.config.tip.ErrorTip;
import com.cnaidun.police.config.tip.SuccessTip;
import com.cnaidun.police.config.tip.Tip;
import com.cnaidun.police.entity.RequestInfo;
import com.cnaidun.police.entity.RolePermission;
import com.cnaidun.police.mapper.RequestInfoMapper;
import com.cnaidun.police.mapper.RolePermissionMapper;
import com.cnaidun.police.service.PermissionService;
import com.cnaidun.police.service.RequestInfoService;
import com.cnaidun.police.vo.RequestInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName RequestInfoServiceImpl
 * @Description TODO
 * @Author zhouyw
 * @Date 2019/12/9 15:44
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class, readOnly = false)
@Slf4j
public class RequestInfoServiceImpl extends ServiceImpl<RequestInfoMapper, RequestInfo> implements RequestInfoService {

    @Autowired
    private RequestInfoMapper requestInfoMapper;

    @Override
    public PagedResult<RequestInfo> getUserLog(int pageNo, int pageSize) {
        PageRequest pageRequest = new PageRequest(pageNo, pageSize, true);
        List<RequestInfo> requestInfos = requestInfoMapper.selectPage(new Page<Object>(pageNo, pageSize), new EntityWrapper<RequestInfo>().orderBy("create_time"));
        List<RequestInfo> requestInfos1 = requestInfoMapper.selectList(new EntityWrapper<RequestInfo>());
        pageRequest.setTotal(requestInfos1.size());
        return BeanUtil.toPagedResult(requestInfos, pageRequest);
    }

    @Override
    public Tip deleteLog(List<Long> id) {
        if (CollectionUtils.isEmpty(id)) {
            return new ErrorTip("请选择要删除的日志");
        }
        return new SuccessTip(requestInfoMapper.deleteBatchIds(id));
    }

    @Override
    public Tip clearLog() {
        return new SuccessTip(requestInfoMapper.clearLog());
    }
}
