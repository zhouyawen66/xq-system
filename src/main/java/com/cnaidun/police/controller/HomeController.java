package com.cnaidun.police.controller;

import com.cnaidun.police.config.tip.SuccessTip;
import com.cnaidun.police.config.tip.Tip;
import com.cnaidun.police.service.TbScoreSituationRecordService;
import com.cnaidun.police.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HomeController
 * @Descriprion
 * @Author xuejnqiang
 * @Date 2019/12/5 9:56
 * @Version 1.0
 * 数据分析模块
 **/

@Api(description = "首页")
@RestController
@RequestMapping("/home")
@Slf4j
public class HomeController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private TbScoreSituationRecordService tbScoreSituationRecordService;

    /**
     * 首页饼图
     * xuejunqiang
     *
     * @return
     */
    @ApiOperation(value = "首页饼图")
    @PostMapping(value = "/pie")
    public Tip pie() {
        return userInfoService.findUserCountByArea();
    }


    @ApiOperation(value = "区季度统计")
    @PostMapping(value = "/getScoreDataByYearAndMonth")
    public Tip selectMerchantInfo(String year) {
        return tbScoreSituationRecordService.getTjDataByYearAndMonth(year);
    }

    @ApiOperation(value = "获取每个区每个月的统计分值")
    @PostMapping(value = "/getAllMonthTjData")
    public Tip getAllMonthTjData(String year) {
        return tbScoreSituationRecordService.getAllMonthTjData(year);
    }
//
//    @ApiOperation(value = "消费人数统计")
//    @PostMapping(value = "/getTransationNum")
//    public Tip getTransationNum(@RequestParam String begin, @RequestParam String end) {
//        return new SuccessTip(tbScoreSituationRecordService.getTransationNum(begin, end));
//    }
}