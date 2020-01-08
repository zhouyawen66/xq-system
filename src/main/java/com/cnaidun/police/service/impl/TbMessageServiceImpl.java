package com.cnaidun.police.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cnaidun.police.config.mybatis.BeanUtil;
import com.cnaidun.police.config.mybatis.PageRequest;
import com.cnaidun.police.config.mybatis.PagedResult;
import com.cnaidun.police.entity.TbMerchantGoods;
import com.cnaidun.police.entity.TbMerchantInfo;
import com.cnaidun.police.entity.TbMessage;
import com.cnaidun.police.mapper.TbMerchantGoodsMapper;
import com.cnaidun.police.mapper.TbMessageMapper;
import com.cnaidun.police.service.TbMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.List;

/**
 * @ClassName TbMessageServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2019/12/4 15:19
 * @Version 1.0
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class, readOnly = false)
public class TbMessageServiceImpl extends ServiceImpl<TbMessageMapper, TbMessage> implements TbMessageService {

    @Autowired
    private TbMessageMapper tbMessageMapper;

    private static String  COMMUNITY ="1";

    //查询消息列表
    @Override
    public PagedResult<TbMessage> selectMessage(int pageNo, int pageSize) {
        PageRequest pageRequest = new PageRequest(pageNo, pageSize, true);
        List<TbMessage> tbMerchantInfos = tbMessageMapper.selectMessage(pageRequest);
        return BeanUtil.toPagedResult(tbMerchantInfos, pageRequest);
    }


    @Override
    public List<TbMessage> selectMessageById(Long userId) {
        return tbMessageMapper.selectMessageById(userId);
    }

    //插入消息列表
    @Override
    public int insertMessage(TbMessage tbMessage) {
        tbMessage.setMsgType(COMMUNITY);
        int result = tbMessageMapper.insert(tbMessage);
        return result;
    }

    //通过id修改消息
    @Override
    public int updateMessageById(TbMessage tbMessage) {
        return tbMessageMapper.update(tbMessage, new EntityWrapper<TbMessage>().eq("id", tbMessage.getId()));
    }


    //逻辑删除消息列表
    @Override
    public boolean updateMessageBystatus(long id) {
        int result = tbMessageMapper.updateMessageBystatus(id);
        if (result != 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据姓名模糊查询
     */
    @Override
    public PagedResult<TbMessage> selectByName(int pageNo, int pageSize, String sender, String recipient, String content) {

        PageRequest pageRequest = new PageRequest(pageNo, pageSize, true);
        List<TbMessage> tbMessages = tbMessageMapper.selectByName(pageRequest, sender, recipient, content);
        return BeanUtil.toPagedResult(tbMessages, pageRequest);
    }
}



