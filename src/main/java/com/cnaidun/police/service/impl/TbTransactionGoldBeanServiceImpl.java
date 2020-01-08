package com.cnaidun.police.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cnaidun.police.entity.TbTransactionGoldBean;
import com.cnaidun.police.mapper.TbTransactionGoldBeanMapper;
import com.cnaidun.police.service.TbTransactionGoldBeanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName TbTransactionGoldBeanServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2019/12/4 15:20
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class,readOnly = true)
@Slf4j
public class TbTransactionGoldBeanServiceImpl extends ServiceImpl<TbTransactionGoldBeanMapper, TbTransactionGoldBean> implements TbTransactionGoldBeanService {


}
