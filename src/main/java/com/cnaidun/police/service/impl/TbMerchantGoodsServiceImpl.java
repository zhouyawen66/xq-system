package com.cnaidun.police.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cnaidun.police.config.mybatis.BeanUtil;
import com.cnaidun.police.config.mybatis.PageRequest;
import com.cnaidun.police.config.mybatis.PagedResult;
import com.cnaidun.police.entity.TbMerchantGoods;
import com.cnaidun.police.mapper.TbMerchantGoodsMapper;
import com.cnaidun.police.service.TbMerchantGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName TbMerchantGoodsServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2019/12/4 15:18
 * @Version 1.0
 */
@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
@Slf4j
public class TbMerchantGoodsServiceImpl extends ServiceImpl<TbMerchantGoodsMapper, TbMerchantGoods> implements TbMerchantGoodsService {

    @Autowired
    private TbMerchantGoodsMapper tbMerchantGoodsMapper;

    //展示商户 商品信息列表
    @Override
    public PagedResult<TbMerchantGoods> selectMerchantGoods(int pageNo, int pageSize) {
        PageRequest pageRequest = new PageRequest(pageNo, pageSize, true);
        List<TbMerchantGoods> tbMerchantGoods = tbMerchantGoodsMapper.selectMerchantGoods(pageRequest);
        return BeanUtil.toPagedResult(tbMerchantGoods, pageRequest);
    }

    //展示商品详情
    @Override
    public List<TbMerchantGoods> selectMerchantGoodsById(Long id) {
        return tbMerchantGoodsMapper.selectMerchantGoodsById(id);
    }

    //修改商品列表
    @Override
    public int updateMerchantGoods(TbMerchantGoods tbMerchantGoods) {

        return tbMerchantGoodsMapper.update(tbMerchantGoods, new EntityWrapper<TbMerchantGoods>().eq("id", tbMerchantGoods.getId()));
    }

    //新增商品
    @Override
    public int insertGoods(TbMerchantGoods tbMerchantGoods) {
        return tbMerchantGoodsMapper.insertGoods(tbMerchantGoods);
    }
}
