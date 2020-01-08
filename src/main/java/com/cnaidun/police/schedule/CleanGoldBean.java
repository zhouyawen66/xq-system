package com.cnaidun.police.schedule;

import com.cnaidun.police.dto.AddTranGoldBeanDTO;
import com.cnaidun.police.dto.HaveGoldBeanDTO;
import com.cnaidun.police.mapper.TbTransactionGoldBeanMapper;
import com.cnaidun.police.mapper.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CleanGoldBean
 * @Descriprion 季度结束清除金豆
 * @Author dongyin
 * @Date 2019/11/19 18:39
 **/
@Slf4j
@Component
@EnableScheduling
public class CleanGoldBean {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    TbTransactionGoldBeanMapper transactionGoldBeanMapper;

    ThreadPoolExecutor executor = new ThreadPoolExecutor(5,50,3, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100),new ThreadPoolExecutor.CallerRunsPolicy());

    @Scheduled(cron = "0 59 23 28-31 3,6,9,12 ?")
//    @Scheduled(cron = "0 */1 * * * ?")
    private void cleanGoldBean(){
        final Calendar c = Calendar.getInstance();
        if (c.get(Calendar.DATE) == c.getActualMaximum(Calendar.DATE)) {
            //是最后一天
            log.info("------------------金豆季度清0开始------------------");
            List<HaveGoldBeanDTO> list = userInfoMapper.findHaveGoldBeanUsers();
            List<Long> updateIds = new ArrayList<>();
            List<AddTranGoldBeanDTO> insertData = new ArrayList<>();
            CountDownLatch countDownLatch = new CountDownLatch(list.size());
            executor.submit(() -> {
                for (HaveGoldBeanDTO dto: list) {
                    AddTranGoldBeanDTO tran = new AddTranGoldBeanDTO();
                    tran.setUserId(dto.getId());
                    tran.setTranContent("季度清0");
                    tran.setTranNum("-"+ dto.getGoldBean());
                    tran.setTranSource(dto.getCommunityUnitName());
                    tran.setTranType("2");
                    insertData.add(tran);
                    updateIds.add(dto.getId());
                    countDownLatch.countDown();
                }
            });
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (CollectionUtils.isEmpty(updateIds)) {
                log.info("------------------没有需要金豆季度清0的，结束------------------");
                return;
            }
            userInfoMapper.updateBoldBean0(updateIds);
            transactionGoldBeanMapper.insertGoldBeanTrans(insertData);
            log.info("------------------金豆季度清0成功------------------");
        }
    }
}
