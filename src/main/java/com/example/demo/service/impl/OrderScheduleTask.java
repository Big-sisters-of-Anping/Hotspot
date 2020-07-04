package com.example.demo.service.impl;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 类名称: OrderScheduleTask
 * 类描述: 预约定时处理任务
 *
 * @author: wqy
 * 创建时间: 2020/7/4 11:41 下午
 * Version 1.0
 */

@Configuration
@EnableScheduling
public class OrderScheduleTask {
    @Scheduled(cron = "0 0 23 * * ?")
    private void scheduledOrder(){
        System.out.println("Execute order scheduler ----------------");
        Runtime runtime = Runtime.getRuntime();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(runtime.exec("ifconfig").getInputStream()));
            String line = null;
            StringBuffer b = new StringBuffer();
            while ((line=br.readLine())!=null) {
                b.append(line+"\n");
            }
            System.out.println(b.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("End order scheduler --------------------");
    }
}