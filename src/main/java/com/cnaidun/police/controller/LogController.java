package com.cnaidun.police.controller;

import com.cnaidun.police.config.mybatis.PagedResult;
import com.cnaidun.police.config.tip.SuccessTip;
import com.cnaidun.police.config.tip.Tip;
import com.cnaidun.police.entity.RequestInfo;
import com.cnaidun.police.service.RequestInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName LogController
 * @Description TODO
 * @Author zyw
 * @Date 2019/12/11 9:24
 * @Version 1.0
 */
@Api(description = "日志")
@RestController
@RequestMapping("/log")
@Slf4j
public class LogController {

    @Autowired
    private RequestInfoService requestInfoService;


    @ApiOperation(value = "获得用户日志行为信息列表")
    @PostMapping(value = "/getList")
    public Tip getLogList(@RequestParam int pageNo, @RequestParam int pageSize) {
        return new SuccessTip(requestInfoService.getUserLog(pageNo,pageSize));
    }

    @ApiOperation(value = "删除日志")
    @PostMapping(value = "/deleteLog")
    public Tip deleteLog(@RequestParam List<Long> id) {
        return new SuccessTip(requestInfoService.deleteLog(id));
    }

//    @ApiOperation(value = "清空日志")
    @PostMapping(value = "/clear")
    public Tip clearLog() {
        return requestInfoService.clearLog();
    }


}
