package com.cnaidun.police.service;

import com.baomidou.mybatisplus.service.IService;
import com.cnaidun.police.config.mybatis.PagedResult;
import com.cnaidun.police.entity.TbMerchantGoods;

import java.util.List;

public interface TbMerchantGoodsService extends IService<TbMerchantGoods> {

    //展示商户商品列表
    PagedResult<TbMerchantGoods> selectMerchantGoods(int pageNo, int pageSize);

    //展示商品详情
    List<TbMerchantGoods> selectMerchantGoodsById(Long id);

    //修改商品列表
    int updateMerchantGoods(TbMerchantGoods tbMerchantGoods);

    //新增商品
    int insertGoods(TbMerchantGoods tbMerchantGoods);

}
