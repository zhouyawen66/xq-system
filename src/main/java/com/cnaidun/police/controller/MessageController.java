package com.cnaidun.police.controller;

import com.cnaidun.police.config.mybatis.PagedResult;
import com.cnaidun.police.config.tip.SuccessTip;
import com.cnaidun.police.config.tip.Tip;
import com.cnaidun.police.entity.TbMessage;
import com.cnaidun.police.service.TbMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author kitty_zhu
 * @date 2019-12-06 15:30
 */
@Slf4j
@Api(tags = "消息模块")
@RestController
@RequestMapping(value = "/Message")
public class MessageController {

    @Autowired
    private TbMessageService tbMessageService;

    @ApiOperation(value = "查询消息列表")
    @PostMapping(value = "/selectMessage")
    public Tip selectMessage(@ApiParam(value = "pageNo") @RequestParam Integer pageNo,
                             @ApiParam(value = "pageSize") @RequestParam Integer pageSize) {

        PagedResult<TbMessage> tbMessagePagedResult = tbMessageService.selectMessage(pageNo, pageSize);
        return new SuccessTip(tbMessagePagedResult);
    }

    @ApiOperation(value = "查询消息详情")
    @PostMapping(value = "/selectMessageById")
    public Tip selectMessageById(@ApiParam(value = "Id") @RequestParam Long Id) {

        return new SuccessTip(tbMessageService.selectMessageById(Id));
    }


    @ApiOperation(value = "插入消息列表")
    @PostMapping(value = "/insertMessage")
    public Tip insertMessage(@Valid TbMessage tbMessage) {
        return new SuccessTip(tbMessageService.insertMessage(tbMessage));
    }

    @ApiOperation(value = "修改消息列表")
    @PostMapping(value = "/updateMessageById")
    public Tip updateMessageById(@Valid TbMessage tbMessage) {
        return new SuccessTip(tbMessageService.updateMessageById(tbMessage));
    }


    @ApiOperation(value = "删除消息列表")
    @PostMapping(value = "/updateMessageBystatus")
    public Tip updateMessageBystatus(@ApiParam(value = "id") @RequestParam(value = "id") long id) {
        boolean result = tbMessageService.updateMessageBystatus(id);
        return new SuccessTip(result);
    }


    @ApiOperation(value = "模糊搜索消息")
    @PostMapping(value = "/selectByName")
    public Tip selectByName(@ApiParam(value = "pageNo") @RequestParam Integer pageNo,
                            @ApiParam(value = "pageSize") @RequestParam Integer pageSize,
                            @ApiParam(value = "sender") @RequestParam(value = "sender") String sender,
                            @ApiParam(value = "recipient") @RequestParam(value = "recipient") String recipient,
                            @ApiParam(value = "content") @RequestParam(value = "content") String content) {
        TbMessage tbMessage = new TbMessage();
        PagedResult<TbMessage> tbMessagePagedResult = tbMessageService.selectByName(pageNo, pageSize, sender, recipient, content);

        return new SuccessTip(tbMessagePagedResult);
    }

}
