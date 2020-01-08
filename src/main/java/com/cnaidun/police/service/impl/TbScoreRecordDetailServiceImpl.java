package com.cnaidun.police.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cnaidun.police.entity.TbScoreRecordDetail;
import com.cnaidun.police.mapper.TbScoreRecordDetailMapper;
import com.cnaidun.police.service.TbScoreRecordDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName TbScoreRecordDetailServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2019/12/4 15:19
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class,readOnly = true)
@Slf4j
public class TbScoreRecordDetailServiceImpl extends ServiceImpl<TbScoreRecordDetailMapper, TbScoreRecordDetail> implements TbScoreRecordDetailService {
}
