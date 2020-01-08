package com.cnaidun.police.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cnaidun.police.config.mybatis.PageRequest;
import com.cnaidun.police.entity.TbMerchantInfo;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author kitty_zhu
 * @date 2019-12-04 15:20
 */
@Mapper
public interface TbMerchantInfoMapper extends BaseMapper<TbMerchantInfo> {

    //查询商户列表
    List<TbMerchantInfo> selectMerchantInfo(PageRequest pageRequest);

    //通过id查询商户详情
    List<TbMerchantInfo> selectMerchantInfoById(@Param("id") Long id);

    //通过商户id修改商户信息
    int updateMerchantInfoById(TbMerchantInfo tbMerchantInfo);


}
