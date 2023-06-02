package com.bigdata.backstage.modules.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bigdata.backstage.modules.common.mapper.MetDataColumnMapper;
import com.bigdata.backstage.modules.common.mapper.MetDwInfoMapper;
import com.bigdata.backstage.modules.common.model.MetDataColumn;
import com.bigdata.backstage.modules.common.model.MetDwInfo;
import com.bigdata.backstage.modules.common.model.MetDwTableMapInfo;
import com.bigdata.backstage.modules.common.service.MetDataColumnService;
import com.bigdata.backstage.modules.common.service.MetDwInfoService;
import com.bigdata.backstage.modules.source.dto.DataSourceInfoDto;
import com.bigdata.backstage.modules.source.dto.DataSourcePageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 数据资产-ODPS表字段 服务实现类
 * </p>
 *
 * @author bigdata
 * @since 2023-02-08
 */
@Service
public class MetDataColumnServiceImpl extends ServiceImpl<MetDataColumnMapper, MetDataColumn> implements MetDataColumnService {


    @Autowired
    private MetDwInfoMapper metDwInfoMapper;
    @Autowired
    private MetDwInfoService metDwInfoService;

    // 获取数仓的映射名
    public String getTableNameMetaDetailOutline(Integer dwId) {
        MetDwTableMapInfo dwMapInfo = metDwInfoService.getDwMapInfo(dwId);
        // 获取需要查询的表名
        return dwMapInfo.getMapMetaDetailOutline();
    }

    //同步字段级别
    @Override
    public void syncColumn() {
        List<MetDwInfo> metDwInfos = metDwInfoMapper.selectList(null);
        for (MetDwInfo metDwInfo : metDwInfos) {
            Integer dwId = metDwInfo.getId();
            String tableName = getTableNameMetaDetailOutline(dwId);
            baseMapper.syncColumnInsert(dwId,tableName);
            baseMapper.syncColumnUpdate(dwId,tableName);
//            baseMapper.syncColumnDelete(dwId,tableName);
//            baseMapper.syncColumnRecover(dwId,tableName);
        }

    }

    //获取表信息
    @Override
    public List<MetDataColumn> getColumnInfo(DataSourceInfoDto dto) {
        QueryWrapper<MetDataColumn> wrapper = new QueryWrapper<>();
        wrapper.eq("tbl_name", dto.getTableName());
        wrapper.eq("dw_id", dto.getDwId());
        return baseMapper.selectList(wrapper);
    }
}
