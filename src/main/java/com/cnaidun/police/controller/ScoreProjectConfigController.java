package com.cnaidun.police.controller;

import com.cnaidun.police.config.mybatis.PagedResult;
import com.cnaidun.police.config.tip.ErrorTip;
import com.cnaidun.police.config.tip.SuccessTip;
import com.cnaidun.police.config.tip.Tip;
import com.cnaidun.police.dto.ProjectConfigDTO;
import com.cnaidun.police.interceptor.ratelimit.RateLimit;
import com.cnaidun.police.service.TbScoreProjectConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @ClassName ScoreProjectConfigController
 * @Descriprion TODO
 * @Author dongyin
 * @Date 2019/12/6 11:10
 **/
@Slf4j
@Api(tags = "打分项配置控制层")
@RestController
@RequestMapping(value = "/scoreProject")
public class ScoreProjectConfigController {
    @Autowired
    TbScoreProjectConfigService scoreProjectConfigService;

    @ApiOperation("打分项列表")
    @PostMapping("/findProjectConfigList")
    public Tip findProjectConfigList(@RequestParam Integer pageNo, @RequestParam Integer pageSize) {
        PagedResult<ProjectConfigDTO> roleList = scoreProjectConfigService.findProjectConfigList(pageNo, pageSize);
        return new SuccessTip(roleList);
    }

    @ApiOperation("添加、修改打分项")
    @PostMapping("/addOrUpdateConfig")
    @RateLimit(lockName = "addOrUpdateConfig", keys = "projectConfigDTO.projectContent")
    public Tip addOrUpdateConfig(@Valid ProjectConfigDTO projectConfigDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ErrorTip(bindingResult.getFieldError().getDefaultMessage());
        }
        return scoreProjectConfigService.addOrUpdateConfig(projectConfigDTO);
    }

    @ApiOperation("打分项启用、禁用")
    @PostMapping("/setStatus")
    public Tip setStatus(@RequestParam String status, @RequestParam Long id) {
        Tip tip = scoreProjectConfigService.setStatus(status, id);
        return tip;
    }
}
