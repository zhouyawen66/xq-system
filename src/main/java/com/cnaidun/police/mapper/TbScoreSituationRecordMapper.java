package com.cnaidun.police.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cnaidun.police.config.tip.Tip;
import com.cnaidun.police.dto.HomeDTO;
import com.cnaidun.police.dto.MonthDTO;
import com.cnaidun.police.dto.QuarterDTO;
import com.cnaidun.police.entity.TbScoreSituationRecord;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TbScoreSituationRecordMapper extends BaseMapper<TbScoreSituationRecord> {

    /**
     * 统计根据季度
     */
    List<Map<String, String>> getTjDataByYearAndMonth(@Param("year") Integer year, @Param("monbegin") Integer monbegin, @Param("monend") Integer monend);

    /**
     * 获取所有月份社区的分值
     */
    List<Map<String, String>> getAllMonthTjData();

    /**
     * 按时间统计消费人数
     */
    Integer getTransationNum(@Param("begin") String begin, @Param("end") String end);

    List<HomeDTO> selectYjDataByYearAndQuarter(@Param("year") String year);

    List<MonthDTO> selectTjDataByMonth(@Param("year") String year);

    List<HomeDTO> selectTjDataByMonth2(String year);
}
