package com.cnaidun.police.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cnaidun.police.config.mybatis.PageRequest;
import com.cnaidun.police.entity.TbMessage;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author kitty_zhu
 * @date 2019-12-04 15:20
 */
@Mapper
public interface TbMessageMapper extends BaseMapper<TbMessage> {

    //查询消息列表
    List<TbMessage> selectMessage(PageRequest pageRequest);

    /**
     * 通过id查询消息详情
     */
    List<TbMessage> selectMessageById(@Param("Id") Long Id);

    //插入消息列表
    int insertMessage(TbMessage tbMessage);

    //修改消息
    int updateMessageById(TbMessage tbMessage);

    //逻辑删除消息
    int updateMessageBystatus(@Param("id") Long id);

    //根据姓名模糊查询
    List<TbMessage> selectByName(PageRequest pageRequest,
                                 @ApiParam("sender") String sender,
                                 @ApiParam("recipient") String recipient,
                                 @ApiParam("content") String content);
}

