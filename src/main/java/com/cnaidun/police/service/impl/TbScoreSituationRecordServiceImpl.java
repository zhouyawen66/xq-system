package com.cnaidun.police.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cnaidun.police.config.tip.SuccessTip;
import com.cnaidun.police.config.tip.Tip;
import com.cnaidun.police.dto.HomeDTO;
import com.cnaidun.police.dto.MonthDTO;
import com.cnaidun.police.dto.QuarterDTO;
import com.cnaidun.police.entity.TbScoreSituationRecord;
import com.cnaidun.police.mapper.TbScoreSituationRecordMapper;
import com.cnaidun.police.service.TbScoreSituationRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName TbScoreSituationRecordServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2019/12/4 15:20
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
@Slf4j
public class TbScoreSituationRecordServiceImpl extends ServiceImpl<TbScoreSituationRecordMapper, TbScoreSituationRecord> implements TbScoreSituationRecordService {
    @Autowired
    private TbScoreSituationRecordMapper tbScoreSituationRecordMapper;

    public Tip getTjDataByYearAndMonth(String year) {
        //先按年份取出每个分区每个季度的分数,如果月份不传则查所有
        List<HomeDTO> homeDTOS = tbScoreSituationRecordMapper.selectYjDataByYearAndQuarter(year);
        //map的K是分区名V是分区中的数据
        Map<String, QuarterDTO> map = new HashMap<>();
        for (HomeDTO dto : homeDTOS) {
            Set<String> keys = map.keySet();//取出所有的Key
            String name = dto.getName();//取出分区名做为Key
            //如果Map中有key则编辑数据如果没有则新建数据
            if (keys.contains(name)) {
                QuarterDTO quarterDTO = map.get(name);
                Integer score = dto.getScore();//取出分数
                //按照季度分类后加上分数
                switch (dto.getQuarter()) {
                    case "one":
                        quarterDTO.setOne(quarterDTO.getOne() + score);//一季度
                        break;
                    case "two":
                        quarterDTO.setTwo(quarterDTO.getTwo() + score);//二季度
                        break;
                    case "three":
                        quarterDTO.setThree(quarterDTO.getThree() + score);//三季度
                        break;
                    case "four":
                        quarterDTO.setFour(quarterDTO.getFour() + score);//四季度
                        break;
                    default:
                        break;
                }
                map.put(name, quarterDTO);
            } else {
                QuarterDTO quarterDTO = new QuarterDTO();
                quarterDTO.setName(name);
                map.put(name, quarterDTO);
            }
        }
        //取出Map中所有的数据,返回
        List<QuarterDTO> data = new ArrayList<>();
        for (QuarterDTO quarterDTO : map.values()) {
            data.add(quarterDTO);
        }
        return new SuccessTip(data);
    }

    public Tip getAllMonthTjData(String year) {
        //先查出每个月的每个片区的数据
        List<MonthDTO> monthDTOS = tbScoreSituationRecordMapper.selectTjDataByMonth(year);

//        List<HomeDTO> homeDTOS = tbScoreSituationRecordMapper.selectTjDataByMonth2(year);
//
//        if(!monthDTOS.isEmpty()){
//            return new SuccessTip(monthDTOS);
//        }

        List<HomeDTO> homeDTOS = new ArrayList<>();
        Map<String, Map<String, Integer>> map = new HashMap<>();
        for (MonthDTO dto : monthDTOS) {
            Set<String> keySet = map.keySet();
            Map<String, Integer> data = new HashMap<>();

            String name = dto.getName();//取出分区作为Key

            if (keySet.contains(name)) {
                data = map.get(name);
            } else {
                data.put("1", 0);
                data.put("2", 0);
                data.put("3", 0);
                data.put("4", 0);
                data.put("5", 0);
                data.put("6", 0);
                data.put("7", 0);
                data.put("8", 0);
                data.put("9", 0);
                data.put("10", 0);
                data.put("11", 0);
                data.put("12", 0);
            }
            String month = dto.getMonth();//月份
            data.put(month, data.get(month) + dto.getData());
            map.put(name,data);
        }
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            homeDTOS.add(HomeDTO.builder()
                    .name(key)
                    .data(map.get(key))
                    .build());
        }
        return new SuccessTip(homeDTOS);
    }

    public Integer getTransationNum(String begin, String end) {
        return tbScoreSituationRecordMapper.getTransationNum(begin, end);
    }
}