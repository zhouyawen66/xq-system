package com.cnaidun.police.controller;

import com.alibaba.fastjson.JSONObject;
import com.cnaidun.police.config.mybatis.PagedResult;
import com.cnaidun.police.config.tip.SuccessTip;
import com.cnaidun.police.config.tip.Tip;
import com.cnaidun.police.dto.RoleListResponseDTO;
import com.cnaidun.police.entity.TbMerchantGoods;
import com.cnaidun.police.entity.TbMerchantInfo;
import com.cnaidun.police.mapper.TbMerchantGoodsMapper;
import com.cnaidun.police.service.TbMerchantGoodsService;
import com.cnaidun.police.service.TbMerchantInfoService;
import com.cnaidun.police.vo.PageVo;
import com.cnaidun.police.vo.TbMerchantGoodsVo;
import com.cnaidun.police.vo.TbMerchantInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author kitty_zhu
 * @date 2019-12-04 16:59
 */
@Slf4j
@Api(tags = "商户模块")
@RestController
@RequestMapping(value = "/TbMerchantInfo")
public class TbMerchantInfoController {

    @Autowired
    private TbMerchantInfoService tbMerchantInfoService;

    @Autowired
    private TbMerchantGoodsService tbMerchantGoodsService;

    @Autowired
    private TbMerchantGoodsMapper tbMerchantGoodsMapper;

    @ApiOperation(value = "展示商户信息列表")
    @PostMapping(value = "/selectMerchantInfo")
    public Tip selectMerchantInfo(@ApiParam(value = "pageNo", required = true) @RequestParam Integer pageNo,
                                  @ApiParam(value = "pageSize", required = true) @RequestParam Integer pageSize) {

        PagedResult<TbMerchantInfo> merchanList = tbMerchantInfoService.selectMerchantInfo(pageNo, pageSize);
        return new SuccessTip(merchanList);
    }

    @ApiOperation(value = "展示商户详情")
    @PostMapping(value = "/selectMerchantInfoById")
    public Tip selectMerchantInfoById(@ApiParam(value = "商户id", required = true) @RequestParam(value = "userId") Long userId) {
        return new SuccessTip(tbMerchantInfoService.selectMerchantInfoById(userId));
    }


    @ApiOperation(value = "完善(修改)商户信息")
    @PostMapping(value = "/updateMerchantInfo")
    public Tip updateMerchantInfo(@Valid TbMerchantInfoVo tbMerchantInfoVo) {
        TbMerchantInfo tbMerchantInfo = new TbMerchantInfo();
        BeanUtils.copyProperties(tbMerchantInfoVo, tbMerchantInfo);
        int result = tbMerchantInfoService.updateMerchantInfoById(tbMerchantInfo);
        return new SuccessTip(result);
    }

    @ApiOperation(value = "展示商品信息列表")
    @PostMapping(value = "/selectMerchantGoods")
    public Tip selectMerchantGoods(@RequestParam Integer pageNo, @RequestParam Integer pageSize) {

        PagedResult<TbMerchantGoods> goodsList = tbMerchantGoodsService.selectMerchantGoods(pageNo, pageSize);
        return new SuccessTip(goodsList);
    }

    @ApiOperation(value = "展示商品详情")
    @PostMapping(value = "/selectMerchantGoodsById")
    public Tip selectMerchantGoodsById(@ApiParam(value = "id", required = true) @RequestParam(value = "id") Long id) {
        return new SuccessTip(tbMerchantGoodsService.selectMerchantGoodsById(id));
    }

    @ApiOperation(value = "修改商品信息列表")
    @PostMapping(value = "/updateMerchantGoods")
    public Tip updateMerchantGoods(@Valid TbMerchantGoodsVo tbMerchantGoodsVo) {
        TbMerchantGoods tbMerchantGoods = new TbMerchantGoods();
        BeanUtils.copyProperties(tbMerchantGoodsVo, tbMerchantGoods);
        int result = tbMerchantGoodsService.updateMerchantGoods(tbMerchantGoods);
        return new SuccessTip(result);
    }

    @ApiOperation(value = "新增商品信息")
    @PostMapping(value = "/insertGoods")
    public Tip insertGoods(@Valid TbMerchantGoodsVo tbMerchantGoodsVo) throws ParseException {
        TbMerchantGoods tbMerchantGoods = new TbMerchantGoods();
        BeanUtils.copyProperties(tbMerchantGoodsVo, tbMerchantGoods);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = df.parse(tbMerchantGoodsVo.getGoodsActivityStart());
        Date parse1 = df.parse(tbMerchantGoodsVo.getGoodsActivityEnd());
        tbMerchantGoods.setGoodsActivityStart(parse);
        tbMerchantGoods.setGoodsActivityEnd(parse1);

        Integer insert = tbMerchantGoodsMapper.insert(tbMerchantGoods);
        return new SuccessTip(insert);
    }


}

