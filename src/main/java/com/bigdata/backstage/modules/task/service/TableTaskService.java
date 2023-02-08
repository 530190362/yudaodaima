package com.bigdata.backstage.modules.task.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
@EnableScheduling
public class TableTaskService   {

    @Value("${dw.nameEn}")
    private String dwId;

    @Value("${dw.nameEn}")
    private String dwNameEn;

    @Value("${dw.nameZh}")
    private String dwNameZh;

    //数据资产概览表met_data_overview
    //corn每天7点执行
    @Scheduled(cron = "0 0 7 * * ?")
    public void syncTabelOverview(){
        System.out.println(dwId);
        System.out.println(dwNameEn);
        System.out.println(dwNameZh);
        System.out.println("数据资产概览表met_data_overview");
    }


    //数据资产标签表met_data_label
    @Scheduled(cron = "0 0 7 * * ?")
    public void syncTableLabel(){
        System.out.println(dwId);
        System.out.println(dwNameEn);
        System.out.println(dwNameZh);
        System.out.println("数据资产概览表met_data_overview");
    }

}
