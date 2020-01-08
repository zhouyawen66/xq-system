package com.cnaidun.police.service;

import com.baomidou.mybatisplus.service.IService;
import com.cnaidun.police.config.tip.Tip;
import com.cnaidun.police.entity.TbScoreSituationRecord;

import java.awt.*;
import java.util.List;
import java.util.Map;

public interface TbScoreSituationRecordService extends IService<TbScoreSituationRecord> {

    /**
     * 按月统计
     */
    Tip getTjDataByYearAndMonth(String year);

    /**
     * 获取所有月份社区的分值
     */
    Tip getAllMonthTjData(String year);

    /**
     * 按时间统计消费人数
     */
    Integer getTransationNum(String begin, String end);
}
