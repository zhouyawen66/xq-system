package com.cnaidun.police.mapper;


import com.cnaidun.police.config.mybatis.PageRequest;
import com.cnaidun.police.dto.HaveGoldBeanDTO;
import com.cnaidun.police.dto.MenuResponseDTO;
import com.cnaidun.police.dto.StatisticsDTO;
import com.cnaidun.police.dto.SysUserDto;
import com.cnaidun.police.dto.UserInfoRequestDTO;
import com.cnaidun.police.entity.TbScoreSituationRecord;
import com.cnaidun.police.entity.UserInfo;
import com.cnaidun.police.vo.TbScoreRecordDetailVo;
import com.cnaidun.police.vo.TbScoreSituationRecordVo;
import com.cnaidun.police.vo.UserInfoVo;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserInfoMapper {

    UserInfo findByAccount(String account);

    List<MenuResponseDTO> findUserPermissions(String account);

    Integer updateUserInfo(UserInfo userInfo);

    Integer addUserInfo(UserInfo userInfo);

    Integer dropUserInfo(@Param("id") Long id);

    List<Map<String, Object>> queryAll();

    Integer updatePWD(UserInfo userInfo);

    String getSalt(@Param("id") Integer id);

    boolean isPasswordRight(Map<String, Object> map);

    List<UserInfoVo> getAdminList(PageRequest pageRequest,@Param("account")String account);

    List<UserInfoVo> getHouseholdList(PageRequest pageRequest,@Param("account")String account);

    List<TbScoreSituationRecordVo> getUserIntegrateScoreList(PageRequest pageRequest,@Param("id")Long id);

    List<TbScoreRecordDetailVo> getUserScoreList(PageRequest pageRequest,@Param("id")Long id, @Param("userId")Long userId);


    void updateUserStatus(@Param("id")Long id,@Param("status")String status);

    String findAccountByOpenId(String openId);

    Integer updateOpenIdByAccount(Map map);

    List<StatisticsDTO> selectUserCountByArea();

    List<HaveGoldBeanDTO> findHaveGoldBeanUsers();

    void updateBoldBean0(@Param("list") List<Long> list);
}
