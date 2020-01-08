package com.cnaidun.police.service;

import com.baomidou.mybatisplus.service.IService;
import com.cnaidun.police.config.mybatis.PagedResult;
import com.cnaidun.police.entity.TbMerchantInfo;

import java.util.List;

public interface TbMerchantInfoService extends IService<TbMerchantInfo> {

    //查询商户列表
    PagedResult<TbMerchantInfo> selectMerchantInfo(int pageNo, int pageSize);

    //通过id查询商户详情
    List<TbMerchantInfo> selectMerchantInfoById(Long userId);

    //修改商户信息
    int updateMerchantInfoById(TbMerchantInfo tbMerchantInfo);


}

