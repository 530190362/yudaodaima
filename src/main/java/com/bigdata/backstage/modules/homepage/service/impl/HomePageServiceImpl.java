package com.bigdata.backstage.modules.homepage.service.impl;

import com.bigdata.backstage.modules.common.mapper.ViewMetDetailOutlineMapper;
import com.bigdata.backstage.modules.homepage.enums.DataChangeDaysEnum;
import com.bigdata.backstage.modules.homepage.service.HomePageService;
import com.bigdata.backstage.modules.homepage.vo.DataChangeVo;
import com.bigdata.backstage.modules.homepage.vo.DataCountTop;
import com.bigdata.backstage.modules.homepage.vo.DataOverviewVo;
import com.bigdata.backstage.modules.homepage.vo.DataSizeTop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

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
    private ViewMetDetailOutlineMapper metDetailOutlineMapper;

    @Override
    public Map<String, Object> getDataOverview(Long dwID) {
        DataOverviewVo overview = metDetailOutlineMapper.getOverview(dwID);
        Map<String, Object> map = new HashMap<String, Object>();
        if (overview != null) {
            map.put("totalTbl", overview.getTotalTblCount());
            map.put("totalSize", overview.getTotalTblSize().divide(BigDecimal.valueOf(1024), 2, RoundingMode.UP));
            map.put("colCount", overview.getTotalTblCol());
            map.put("rowCount", overview.getTotalTblRow());
            return map;
        }
        return null;
    }

    @Override
    public List<DataChangeVo> queryDataChangeCount(Integer days, Integer dwId) {
        List<DataChangeVo> changeVoist = new ArrayList<>();
        if (DataChangeDaysEnum.SEVEN_DAYS.getKey().equals(days)) {
            changeVoist = metDetailOutlineMapper.getDataChangeNum(DataChangeDaysEnum.SEVEN_DAYS.getValue(), dwId);
        } else if (DataChangeDaysEnum.FIFTEEN_DAYS.getKey().equals(days)) {
            changeVoist = metDetailOutlineMapper.getDataChangeNum(DataChangeDaysEnum.FIFTEEN_DAYS.getValue(), dwId);
        }
        return changeVoist;
    }

    @Override
    public List<DataChangeVo> queryDataChangeSize(Integer days, Integer dwId) {
        List<DataChangeVo> changeVoist = new ArrayList<>();
        if (DataChangeDaysEnum.SEVEN_DAYS.getKey().equals(days)) {
            changeVoist = metDetailOutlineMapper.getDataChangeSize(DataChangeDaysEnum.SEVEN_DAYS.getValue(), dwId);
        } else if (DataChangeDaysEnum.FIFTEEN_DAYS.getKey().equals(days)) {
            changeVoist = metDetailOutlineMapper.getDataChangeSize(DataChangeDaysEnum.FIFTEEN_DAYS.getValue(), dwId);
        }
        return changeVoist;
    }

    @Override
    public List<DataSizeTop> getDataSizeTop(Integer dwId) {
        return metDetailOutlineMapper.getDataSizeTop( dwId);
    }

    @Override
    public List<DataCountTop> getDataCountTop(Integer dwId) {
        return metDetailOutlineMapper.getDataCountTop(dwId);
    }

    @Override
    public List<DataCountTop> getDataNewTop(Integer dwId) {
        return metDetailOutlineMapper.getDataNewTop(dwId);
    }

    @Override
    public List<DataCountTop> getDataNewRowTop(Integer dwId) {
        return metDetailOutlineMapper.getDataNewRowTop(dwId);
    }
}
