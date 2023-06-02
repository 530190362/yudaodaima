package com.bigdata.backstage.modules.common.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bigdata.backstage.modules.common.mapper.MetDataTableMapper;
import com.bigdata.backstage.modules.common.mapper.MetDwInfoMapper;
import com.bigdata.backstage.modules.common.model.MetDataTable;
import com.bigdata.backstage.modules.common.model.MetDwInfo;
import com.bigdata.backstage.modules.common.model.MetDwTableMapInfo;
import com.bigdata.backstage.modules.common.service.MetDataTableService;
import com.bigdata.backstage.modules.common.service.MetDwInfoService;
import com.bigdata.backstage.modules.source.dto.DataSourceHistoryDto;
import com.bigdata.backstage.modules.source.dto.DataSourceInfoDto;
import com.bigdata.backstage.modules.source.dto.DataSourcePageDto;
import com.bigdata.backstage.modules.source.dto.DataSourceTotalDto;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 数据资产-ODPS表 服务实现类
 * </p>
 *
 * @author bigdata
 * @since 2023-02-08
 */
@Service
public class MetDataTableServiceImpl extends ServiceImpl<MetDataTableMapper, MetDataTable> implements MetDataTableService {


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


    //同步表级别
    @Override
    public void syncTable() {
        List<MetDwInfo> metDwInfos = metDwInfoMapper.selectList(null);
        for (MetDwInfo metDwInfo : metDwInfos) {
            Integer dwId = metDwInfo.getId();
            System.out.println(dwId);
            baseMapper.syncTableInsert(dwId);
            baseMapper.syncTableUpdate(dwId);
//            baseMapper.syncTableDelete(dwId);
        }

    }

    //查看表是否存在
    @Override
    public boolean selectTable(String table) {
        QueryWrapper<MetDataTable> wrapper = new QueryWrapper<>();
        wrapper.eq("tbl_name", table);
        Long count = baseMapper.selectCount(wrapper);
        return count > 0;
    }

    //数据集成-3个指标
    @Override
    public DataSourceTotalDto selectOdsTable(Integer dwId) {
        String tableName = getTableNameMetaDetailOutline(dwId);
        return baseMapper.selectOdsIndex(tableName);

    }

    //数据集成-折线图
    @Override
    public List<DataSourceHistoryDto> selectOdsHistory(Integer limit, Integer dwId) {
        String tableName = getTableNameMetaDetailOutline(dwId);
        return baseMapper.selectOdsHistory(limit, tableName);
    }

    //数据集成-表单(分页模糊查询)
    @Override
    public IPage<MetDataTable> selectOdsPage(DataSourcePageDto dto) {
        //多表联查
        MPJLambdaWrapper<MetDataTable> joinWrapper = new MPJLambdaWrapper<>();
        joinWrapper.selectAll(MetDataTable.class)
                .selectAll(MetDwInfo.class)
                .selectAs(MetDwInfo::getDwNameZn, MetDataTable::getDwName)
                .leftJoin(MetDwInfo.class, MetDwInfo::getId, MetDataTable::getDwId)
                .eq("dw_id", dto.getDwId());

        String sourceCode = dto.getSourceCode();
        switch (sourceCode) {
            case "bms":
                joinWrapper.and(w -> w.likeRight("tbl_name", "ods_mysql_")
                        .or()
                        .likeRight("tbl_name", "ods_farmer_")
                        .or()
                        .likeRight("tbl_name", "ods_bms_")
                        .or()
                        .likeRight("tbl_name", "ods_mz_")
                        .or()
                        .likeRight("tbl_name", "ods_hp_")
                        .or()
                        .likeRight("tbl_name", "ods_xx_")
                        .or()
                        .likeRight("tbl_name", "ods_global_")
                        .or()
                        .likeRight("tbl_name", "ods_hh_"));
                break;
            case "ofl":
                joinWrapper.and(w -> w.likeRight("tbl_name", "ods_ofl_"));
                break;
            case "pr":
                joinWrapper.and(w -> w.likeRight("tbl_name", "ods_pr_"));
                break;
            default:
                joinWrapper.and(w -> w.likeRight("tbl_name", "ods_irs_"));
                break;
        }

        String name = dto.getTableName();
        if (!StrUtil.isEmpty(name)) {
            joinWrapper.like("tbl_name", name);
        }

        //分页
        Page<MetDataTable> pageParam = new Page<>(dto.getPageNum(), dto.getPageSize());
        return baseMapper.selectPage(pageParam, joinWrapper).convert(item -> {
            item.setSourceType(dto.getSourceName());
            return item;
        });
    }


    //获取表信息
    @Override
    public MetDataTable getTableInfo(DataSourceInfoDto dto) {
        QueryWrapper<MetDataTable> wrapper = new QueryWrapper<>();
        wrapper.eq("tbl_name", dto.getTableName());
        wrapper.eq("dw_id", dto.getDwId());
        return baseMapper.selectOne(wrapper);
    }

}
