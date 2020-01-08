package com.cnaidun.police.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cnaidun.police.config.mybatis.BeanUtil;
import com.cnaidun.police.config.mybatis.PageRequest;
import com.cnaidun.police.config.mybatis.PagedResult;
import com.cnaidun.police.entity.TbMerchantGoods;
import com.cnaidun.police.entity.TbMerchantInfo;
import com.cnaidun.police.mapper.TbMerchantInfoMapper;
import com.cnaidun.police.service.TbMerchantInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Wrapper;
import java.util.List;

/**
 * @ClassName TbMerchantInfoServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2019/12/4 15:18
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class, readOnly = false)
@Slf4j
public class TbMerchantInfoServiceImpl extends ServiceImpl<TbMerchantInfoMapper, TbMerchantInfo> implements TbMerchantInfoService {

    @Autowired
    private TbMerchantInfoMapper tbMerchantInfoMapper;


    //查询商户列表
    @Override
    public PagedResult<TbMerchantInfo> selectMerchantInfo(int pageNo, int pageSize) {
        PageRequest pageRequest = new PageRequest(pageNo, pageSize, true);
        List<TbMerchantInfo> tbMerchantInfos = tbMerchantInfoMapper.selectMerchantInfo(pageRequest);
        return BeanUtil.toPagedResult(tbMerchantInfos, pageRequest);
    }

    //通过id查询商户详情
    @Override
    public List<TbMerchantInfo> selectMerchantInfoById(Long userId) {
        return tbMerchantInfoMapper.selectMerchantInfoById(userId);
    }

    //完善（修改）商户信息
    @Override
    public int updateMerchantInfoById(TbMerchantInfo tbMerchantInfo) {
        return tbMerchantInfoMapper.update(tbMerchantInfo, new EntityWrapper<TbMerchantInfo>().eq("user_id", tbMerchantInfo.getUserId()));
    }

}

