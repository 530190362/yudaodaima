package com.bigdata.backstage.modules.homepage.service.impl;

import com.bigdata.backstage.common.exception.Asserts;
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
    public Map<String, Object> getDataOverview(Integer dwId) {
        DataOverviewVo overview = null;
        if (dwId == 1) {
            overview = metDetailOutlineMapper.getOverviewGfdn();
        } else if (dwId == 2) {
            overview = metDetailOutlineMapper.getOverviewQygc();
        } else {
            Asserts.fail("数仓id错误");
        }

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
        Integer resultDays = null;
        if (DataChangeDaysEnum.SEVEN_DAYS.getKey().equals(days)) {
            resultDays = DataChangeDaysEnum.SEVEN_DAYS.getValue();
        } else if (DataChangeDaysEnum.FIFTEEN_DAYS.getKey().equals(days)) {
            resultDays = DataChangeDaysEnum.FIFTEEN_DAYS.getValue();
        }
        if (dwId == 1) {
            changeVoist = metDetailOutlineMapper.getDataChangeNumGfdn(resultDays);
        } else if (dwId == 2) {
            changeVoist = metDetailOutlineMapper.getDataChangeNumQygc(resultDays);
        } else {
            Asserts.fail("数仓id错误");
        }
        return changeVoist;
    }

    @Override
    public List<DataChangeVo> queryDataChangeSize(Integer days, Integer dwId) {
        List<DataChangeVo> changeVoist = new ArrayList<>();
        Integer resultDays = null;
        if (DataChangeDaysEnum.SEVEN_DAYS.getKey().equals(days)) {
            resultDays = DataChangeDaysEnum.SEVEN_DAYS.getValue();
        } else if (DataChangeDaysEnum.FIFTEEN_DAYS.getKey().equals(days)) {
            resultDays = DataChangeDaysEnum.FIFTEEN_DAYS.getValue();
        }
        if (dwId == 1) {
            changeVoist = metDetailOutlineMapper.getDataChangeSizeGfdn(resultDays);
        } else if (dwId == 2) {
            changeVoist = metDetailOutlineMapper.getDataChangeSizeQygc(resultDays);
        } else {
            Asserts.fail("数仓id错误");
        }

        return changeVoist;
    }

    @Override
    public List<DataSizeTop> getDataSizeTop(Integer dwId) {
        List<DataSizeTop> dataSizeTop = null;
        if (dwId == 1) {
            dataSizeTop = metDetailOutlineMapper.getDataSizeTopGfdn();
        } else if (dwId == 2) {
            dataSizeTop = metDetailOutlineMapper.getDataSizeTopQygc();
        } else {
            Asserts.fail("数仓id错误");
        }
        return dataSizeTop;

    }

    @Override
    public List<DataCountTop> getDataCountTop(Integer dwId) {
        List<DataCountTop> dataCountTop = null;
        if (dwId == 1) {
            dataCountTop = metDetailOutlineMapper.getDataCountTopGfdn();
        } else if (dwId == 2) {
            dataCountTop = metDetailOutlineMapper.getDataCountTopQygc();
        } else {
            Asserts.fail("数仓id错误");
        }
        return dataCountTop;

    }

    @Override
    public List<DataCountTop> getDataNewTop(Integer dwId) {
        List<DataCountTop> dataNewTop = null;
        if (dwId == 1) {
            dataNewTop = metDetailOutlineMapper.getDataNewTopGfdn();
        } else if (dwId == 2) {
            dataNewTop = metDetailOutlineMapper.getDataNewTopQygc();
        } else {
            Asserts.fail("数仓id错误");
        }
        return dataNewTop;
    }

    @Override
    public List<DataCountTop> getDataNewRowTop(Integer dwId) {

        List<DataCountTop> dataNewRowTop = null;
        if (dwId == 1) {
            dataNewRowTop = metDetailOutlineMapper.getDataNewRowTopGfdn();
        } else if (dwId == 2) {
            dataNewRowTop = metDetailOutlineMapper.getDataNewRowTopQygc();
        } else {
            Asserts.fail("数仓id错误");
        }
        return dataNewRowTop;
    }
}
