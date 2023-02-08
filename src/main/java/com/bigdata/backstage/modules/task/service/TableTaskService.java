package com.bigdata.backstage.modules.task.service;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.bigdata.backstage.modules.common.model.MetDataSyncLog;
import com.bigdata.backstage.modules.common.service.MetDataColumnService;
import com.bigdata.backstage.modules.common.service.MetDataSyncLogService;
import com.bigdata.backstage.modules.common.service.MetDataTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Component
@Service
@EnableScheduling
public class TableTaskService {

    @Autowired
    private MetDataSyncLogService metDataSyncLogService;

    @Autowired
    private MetDataTableService metDataTableService;

    @Autowired
    private MetDataColumnService metDataColumnService;

    @Value("${dw}")
    private Integer dwId;


    //同步表级别
    //corn每天7点执行
    @Scheduled(cron = "0 0 7 * * ?")
    public void syncTabel() {
        mysqlTask("表级");
    }


    //同步字段级别
    //每天7:10执行
    @Scheduled(cron = "0 10 7 * * ?")
    public void syncColumn() {
        mysqlTask("字段级");
    }


    public void mysqlTask(String type) {
        Date startDate = DateUtil.date();
        MetDataSyncLog metDataSyncLog = new MetDataSyncLog();
        metDataSyncLog.setCreateTime(startDate);
        if (type.equals("表级")) {
            metDataSyncLog.setName("同步表级别");
            metDataTableService.syncTable();

        } else if (type.equals("字段级")) {
            metDataSyncLog.setName("同步字段级别");
            metDataColumnService.syncColumn();
        }
        Date endDate = DateUtil.date();
        metDataSyncLog.setEndTime(endDate);
        metDataSyncLog.setSpendTimes((int) DateUtil.between(startDate, endDate, DateUnit.SECOND));
        metDataSyncLogService.save(metDataSyncLog);
    }

}
