package com.huijava.superiorjavablogs.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Slf4j
public class WechatScheduler {

    public static HashMap accessMap = new HashMap(4);
//    @Scheduled(cron="0 0/1 * * * ?") //每分钟执行一次
//    public void statusCheck() {
//        log.info("每分钟执行一次。开始……");
//        //statusTask.healthCheck();
//        log.info("每分钟执行一次。结束。");
//    }

    @Scheduled(fixedRate = 20000)
    public void testTasks() {
        log.info("每20秒执行一次。开始……accessMap={}", accessMap);
        //statusTask.healthCheck();
        if (accessMap.containsKey("expires_in")) {
            Integer time = (Integer) accessMap.get("expires_in");
            if (time < 200) {
                accessMap.clear();
            } else {
                time = time - 20;
                accessMap.put("expires_in", time);
            }
        }
        log.info("每20秒执行一次。结束。");
    }

}
