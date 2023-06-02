package com.bigdata.backstage.modules.homepage.service.impl;

import com.bigdata.backstage.common.exception.Asserts;
import com.bigdata.backstage.modules.common.mapper.HomePageMapper;
import com.bigdata.backstage.modules.common.mapper.ViewMetDetailOutlineMapper;
import com.bigdata.backstage.modules.common.model.MetDwTableMapInfo;
import com.bigdata.backstage.modules.common.service.MetDwInfoService;
import com.bigdata.backstage.modules.homepage.enums.DataChangeDaysEnum;
import com.bigdata.backstage.modules.homepage.service.HomePageService;
import com.bigdata.backstage.modules.homepage.vo.DataChangeVo;
import com.bigdata.backstage.modules.homepage.vo.DataCountTop;
import com.bigdata.backstage.modules.homepage.vo.DataOverviewVo;
import com.bigdata.backstage.modules.homepage.vo.DataSizeTop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * ODPS元数据记录拉链表 服务实现类
 * </p>
 *
 * @author macro
 * @since 2023-02-06
 */
@Service
public class HomePageServiceImpl implements HomePageService {


    @Autowired
    private HomePageMapper homePageMapper;
    @Autowired
    private MetDwInfoService metDwInfoService;


    // 获取数仓的映射名
    public String getTableNameMetaDetailOutline(Integer dwId) {
        MetDwTableMapInfo dwMapInfo = metDwInfoService.getDwMapInfo(dwId);
        // 获取需要查询的表名
        return dwMapInfo.getMapMetaDetailOutline();
    }


    @Override
    public DataOverviewVo getDataOverview(Integer dwId) {
        String tableName = getTableNameMetaDetailOutline(dwId);
        return homePageMapper.getOverview(tableName);

    }

    @Override
    public List<DataChangeVo> getDataChangeCount(Integer days, Integer dwId) {

        Integer resultDays = null;
        if (DataChangeDaysEnum.SEVEN_DAYS.getKey().equals(days)) {
            resultDays = DataChangeDaysEnum.SEVEN_DAYS.getValue();
        } else if (DataChangeDaysEnum.FIFTEEN_DAYS.getKey().equals(days)) {
            resultDays = DataChangeDaysEnum.FIFTEEN_DAYS.getValue();
        }

        String tableName = getTableNameMetaDetailOutline(dwId);
        return homePageMapper.getDataChangeCount(resultDays, tableName);

    }

    @Override
    public List<DataChangeVo> getDataChangeSize(Integer days, Integer dwId) {
        Integer resultDays = null;
        if (DataChangeDaysEnum.SEVEN_DAYS.getKey().equals(days)) {
            resultDays = DataChangeDaysEnum.SEVEN_DAYS.getValue();
        } else if (DataChangeDaysEnum.FIFTEEN_DAYS.getKey().equals(days)) {
            resultDays = DataChangeDaysEnum.FIFTEEN_DAYS.getValue();
        }

        String tableName = getTableNameMetaDetailOutline(dwId);
        return homePageMapper.getDataChangeSize(resultDays, tableName);


    }

    @Override
    public List<DataSizeTop> getDataSizeTop(Integer dwId) {
        String tableName = getTableNameMetaDetailOutline(dwId);
        return homePageMapper.getDataSizeTop(tableName);
    }


    @Override
    public List<DataCountTop> getDataCountTop(Integer dwId) {
        String tableName = getTableNameMetaDetailOutline(dwId);
        return homePageMapper.getDataCountTop(tableName);


    }

    @Override
    public List<DataCountTop> getDataNewTop(Integer dwId) {
        String tableName = getTableNameMetaDetailOutline(dwId);
        return homePageMapper.getDataNewTop(tableName);
    }

    @Override
    public List<DataCountTop> getDataNewRowTop(Integer dwId) {
        String tableName = getTableNameMetaDetailOutline(dwId);
        return homePageMapper.getDataNewRowTop(tableName);

    }
}
