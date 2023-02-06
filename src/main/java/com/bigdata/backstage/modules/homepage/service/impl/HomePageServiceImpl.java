package com.bigdata.backstage.modules.homepage.service.impl;

import com.bigdata.backstage.modules.common.mapper.ViewMetDetailOutlineMapper;
import com.bigdata.backstage.modules.homepage.enums.DataChangeDaysEnum;
import com.bigdata.backstage.modules.homepage.service.HomePageService;
import com.bigdata.backstage.modules.homepage.vo.DataChangeVo;
import com.bigdata.backstage.modules.homepage.vo.DataOverviewVo;
import com.bigdata.backstage.modules.homepage.vo.DataSizeTop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Map<String, Object> getDataOverview() {
        DataOverviewVo overview = metDetailOutlineMapper.getOverview();
        Map<String, Object> map = new HashMap<String, Object>();
        if (overview != null) {
            map.put("totalTbl", overview.getTotalTblCount());
            map.put("totalSize", overview.getTotalTblSize().setScale(2,RoundingMode.HALF_UP));
            map.put("colCount", overview.getTotalTblCol());
            map.put("rowCount", overview.getTotalTblRow());
            return map;
        }
        return null;
    }

    @Override
    public List<DataChangeVo> queryDataChangeCount(Integer days) {
        List<DataChangeVo> changeVoist = new ArrayList<>();
        if (DataChangeDaysEnum.SEVEN_DAYS.getKey().equals(days)){
            changeVoist = metDetailOutlineMapper.getDataChangeNum(DataChangeDaysEnum.SEVEN_DAYS.getValue());
            System.out.println(changeVoist);
//            changeVoist = metDetailOutlineMapper.getDataChangeNum(days);
//            LambdaQueryWrapper<MetRecordZip> wrapper = new LambdaQueryWrapper<MetRecordZip>()
//                    .orderByDesc(MetRecordZip::getUpdateDate)
//                    .last("limit 7");
//            List<MetRecordZip> businessRecordList = metRecordZipService.list(wrapper);
//            for (MetRecordZip metRecordZip : businessRecordList) {
//                DataChangeVo dataChangeVo = new DataChangeVo();
//                dataChangeVo.setDataNum(BigDecimal.valueOf(metRecordZip.getRowCount()));
//                dataChangeVo.setDate(DateUtil.format(metRecordZip.getUpdateDate(),"yyyy-MM-dd"));
//                changeVoist.add(dataChangeVo);
//            }
        }else if (DataChangeDaysEnum.FIFTEEN_DAYS.getKey().equals(days)) {
            changeVoist = metDetailOutlineMapper.getDataChangeNum(DataChangeDaysEnum.FIFTEEN_DAYS.getValue());
        }
        return changeVoist;
    }

    @Override
    public List<DataChangeVo> queryDataChangeSize(Integer days) {
        List<DataChangeVo> changeVoist = new ArrayList<>();
        if (DataChangeDaysEnum.SEVEN_DAYS.getKey().equals(days)){
            changeVoist = metDetailOutlineMapper.getDataChangeSize(DataChangeDaysEnum.SEVEN_DAYS.getValue());
        }else if (DataChangeDaysEnum.FIFTEEN_DAYS.getKey().equals(days)) {
            changeVoist = metDetailOutlineMapper.getDataChangeSize(DataChangeDaysEnum.FIFTEEN_DAYS.getValue());
        }
        return changeVoist;
    }

    @Override
    public List<DataSizeTop> getDataSizeTop() {
        List<DataSizeTop> dataChangeSize = metDetailOutlineMapper.getDataSizeTop();
        return dataChangeSize;
    }
}
