package com.cnaidun.police.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cnaidun.police.config.mybatis.PageRequest;
import com.cnaidun.police.entity.TbMerchantGoods;
import com.cnaidun.police.entity.TbMerchantInfo;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author kitty_zhu
 * @date 2019-12-04 15:19
 */
@Mapper
public interface TbMerchantGoodsMapper extends BaseMapper<TbMerchantGoods> {

    //展示商户 商品列表
    List<TbMerchantGoods> selectMerchantGoods(PageRequest pageRequest);

    //展示商品详情
    List<TbMerchantGoods> selectMerchantGoodsById(@Param("id") Long id);

    //通过商品id修改商品列表
    int updateMerchantGoodsById(TbMerchantGoods tbMerchantGoods);

    //新增商品
    int insertGoods(TbMerchantGoods tbMerchantGoods);
}
