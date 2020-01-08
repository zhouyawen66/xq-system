package com.cnaidun.police.service;

import com.baomidou.mybatisplus.service.IService;
import com.cnaidun.police.config.mybatis.PagedResult;
import com.cnaidun.police.entity.TbMessage;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbMessageService extends IService<TbMessage> {
    //查询消息列表
    PagedResult<TbMessage> selectMessage(int pageNo, int pageSize);


    /**
     * 通过id查询消息详情
     */
    List<TbMessage> selectMessageById(Long Id);

    //插入消息列表
    int insertMessage(TbMessage tbMessage);

    //修改消息
    int updateMessageById(TbMessage tbMessage);

    //逻辑删除消息
    boolean updateMessageBystatus(long id);

    //根据姓名、内容模糊查询
    PagedResult<TbMessage> selectByName(int pageNo, int pageSize, String sender, String recipient, String content);
}
