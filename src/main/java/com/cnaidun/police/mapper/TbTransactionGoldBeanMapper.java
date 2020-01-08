package com.cnaidun.police.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cnaidun.police.dto.AddTranGoldBeanDTO;
import com.cnaidun.police.entity.TbTransactionGoldBean;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author kitty_zhu
 * @date 2019-12-04 15:22
 */
@Mapper
public interface TbTransactionGoldBeanMapper extends BaseMapper<TbTransactionGoldBean> {
    int insertGoldBeanTrans(@Param("list") List<AddTranGoldBeanDTO> list);
}
