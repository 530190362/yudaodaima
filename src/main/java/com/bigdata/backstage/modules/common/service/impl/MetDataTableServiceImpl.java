package com.bigdata.backstage.modules.common.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bigdata.backstage.common.exception.Asserts;
import com.bigdata.backstage.modules.common.mapper.MetDataTableMapper;
import com.bigdata.backstage.modules.common.mapper.MetDwInfoMapper;
import com.bigdata.backstage.modules.common.model.MetDataTable;
import com.bigdata.backstage.modules.common.model.MetDwInfo;
import com.bigdata.backstage.modules.common.service.MetDataTableService;
import com.bigdata.backstage.modules.source.dto.DataSourceHistoryDto;
import com.bigdata.backstage.modules.source.dto.DataSourceInfoDto;
import com.bigdata.backstage.modules.source.dto.DataSourcePageDto;
import com.bigdata.backstage.modules.source.dto.DataSourceTotalDto;
import com.github.yulichang.base.MPJBaseMapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
        DataSourceTotalDto dataSourceTotalDto = null;
        if (dwId == 1) {
            dataSourceTotalDto = baseMapper.selectOdsIndexGfdn();
        } else if (dwId == 2) {
            dataSourceTotalDto = baseMapper.selectOdsIndexQygc();
        } else {
            Asserts.fail("数仓id错误");
        }
        return dataSourceTotalDto;
    }

    //数据集成-折线图
    @Override
    public List<DataSourceHistoryDto> selectOdsHistory(Integer limit, Integer dwId) {
        List<DataSourceHistoryDto> dataSourceHistoryDtos = null;
        if (dwId == 1) {
            dataSourceHistoryDtos = baseMapper.selectOdsHistoryGfdn(limit);
        } else if (dwId == 2) {
            dataSourceHistoryDtos = baseMapper.selectOdsHistoryQygc(limit);
        } else {
            Asserts.fail("数仓id错误");
        }
        return dataSourceHistoryDtos;
    }

    //数据集成-表单(分页模糊查询)
    @Override
    public IPage<MetDataTable> selectOdsPage(DataSourcePageDto dto) {
        //分页
        Integer pageNum = dto.getPageNum();
        Integer pageSize = dto.getPageSize();
        Page<MetDataTable> pageParam = new Page<>(pageNum, pageSize);
        //多表联查及其分页
        MPJLambdaWrapper<MetDataTable> joinMrapper = new MPJLambdaWrapper<>();
        String sourceCode = dto.getSourceCode();
        if (sourceCode.equals("bms")) {
            joinMrapper.selectAll(MetDataTable.class)
                    .selectAll(MetDataTable.class)
                    .selectAs(MetDwInfo::getDwNameZn, MetDataTable::getDwName)
                    .leftJoin(MetDwInfo.class, MetDwInfo::getId, MetDataTable::getDwId)
                    .likeRight("tbl_name", "ods_mysql_")
                    .or()
                    .likeRight("tbl_name", "ods_farmer_")
                    .or()
                    .likeRight("tbl_name", "ods_mz_" )
                    .or()
                    .likeRight("tbl_name", "ods_hp_")
                    .or()
                    .likeRight("tbl_name", "ods_xx_")
                    .or()
                    .likeRight("tbl_name", "ods_global_")
                    .or()
                    .likeRight("tbl_name", "ods_hh_");
        } else if (sourceCode.equals("ofl")) {
            joinMrapper.selectAll(MetDataTable.class)
                    .selectAll(MetDataTable.class)
                    .selectAs(MetDwInfo::getDwNameZn, MetDataTable::getDwName)
                    .leftJoin(MetDwInfo.class, MetDwInfo::getId, MetDataTable::getDwId)
                    .likeRight("tbl_name", "ods_off_");
        } else if (sourceCode.equals("pr")) {
            joinMrapper.selectAll(MetDataTable.class)
                    .selectAll(MetDataTable.class)
                    .selectAs(MetDwInfo::getDwNameZn, MetDataTable::getDwName)
                    .leftJoin(MetDwInfo.class, MetDwInfo::getId, MetDataTable::getDwId)
                    .likeRight("tbl_name", "ods_pr_");
        } else {
            joinMrapper.selectAll(MetDataTable.class)
                    .selectAll(MetDataTable.class)
                    .selectAs(MetDwInfo::getDwNameZn, MetDataTable::getDwName)
                    .leftJoin(MetDwInfo.class, MetDwInfo::getId, MetDataTable::getDwId)
                    .likeRight("tbl_name", "ods_irs_");
        }
        String name = dto.getTableName();
        if (!StrUtil.isEmpty(name)) {
            joinMrapper.like("tbl_name", name);
        }
        Integer dwId = dto.getDwId();
        if (dwId != null) {
            joinMrapper.eq("dw_id", dto.getDwId());
        }
        Page<MetDataTable> viewMetDataTablePage = baseMapper.selectPage(pageParam, joinMrapper);
        long size = viewMetDataTablePage.getSize();
        if (size > 0) {
            viewMetDataTablePage.getRecords().forEach(item -> {
                item.setSourceType(dto.getSourceName());
            });
            return viewMetDataTablePage;
        }
        return null;
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
