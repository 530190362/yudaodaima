package com.bigdata.backstage.modules.explore.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigdata.backstage.modules.common.model.MetDataTable;
import com.bigdata.backstage.modules.common.model.MetDwInfo;
import com.bigdata.backstage.modules.explore.dto.MetExploreReportDto;
import com.bigdata.backstage.modules.explore.dto.MetExploreReportTableDto;
import com.bigdata.backstage.modules.explore.mapper.ViewExportDataColumnMapper;
import com.bigdata.backstage.modules.explore.mapper.ViewExportDataTableMapper;
import com.bigdata.backstage.modules.explore.model.MetExploreReport;
import com.bigdata.backstage.modules.explore.mapper.MetExploreReportMapper;
import com.bigdata.backstage.modules.explore.model.ViewExportDataColumn;
import com.bigdata.backstage.modules.explore.model.ViewExportDataTable;
import com.bigdata.backstage.modules.explore.service.MetExploreReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bigdata.backstage.modules.norm.model.NormDict;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 数据勘探-数据勘探报告表 服务实现类
 * </p>
 *
 * @author bigdata
 * @since 2023-02-10
 */
@Service
public class MetExploreReportServiceImpl extends ServiceImpl<MetExploreReportMapper, MetExploreReport> implements MetExploreReportService {


    @Autowired
    private ViewExportDataTableMapper viewExportDataTableMapper;

    @Autowired
    private ViewExportDataColumnMapper viewExportDataColumnMapper;

    //勘探报告模糊查询
    @Override
    public IPage<ViewExportDataTable> selectPage(MetExploreReportDto dto) {
        //分页查询
        Integer pageNum = dto.getPageNum();
        Integer pageSize = dto.getPageSize();
        Page<ViewExportDataTable> pageParam = new Page<>(pageNum, pageSize);

        //分页多表关联模糊查询
        MPJLambdaWrapper<ViewExportDataTable> joinMrapper = new MPJLambdaWrapper<>();
        joinMrapper.selectAll(ViewExportDataTable.class)
                .selectAs(MetDwInfo::getDwNameZn, ViewExportDataTable::getDwName)
                .leftJoin(MetDwInfo.class, MetDwInfo::getId, ViewExportDataTable::getDwId);

        Long dwId = dto.getDwId();
        String tableComment = dto.getTableComment();
        String tableName = dto.getTableName();
        if (dwId != null) {
            joinMrapper.eq("dw_id", dwId);
        }
        if (!StrUtil.isEmpty(tableName)) {
            joinMrapper.like("table_name", tableName);
        }
        if (!StrUtil.isEmpty(tableComment)) {
            joinMrapper.like("table_comment", tableComment);
        }
        return viewExportDataTableMapper.selectPage(pageParam, joinMrapper);

    }

    //勘探报告表明确信息
    @Override
    public ViewExportDataTable exploreTablInfo(MetExploreReportTableDto dto) {
        QueryWrapper<ViewExportDataTable> wrapper = new QueryWrapper<>();
        wrapper.eq("dw_id", dto.getDwId());
        wrapper.eq("table_name", dto.getTableName());
        return viewExportDataTableMapper.selectOne(wrapper);
    }

    //勘探报告字段明确信息
    @Override
    public List<ViewExportDataColumn> exploreColumnsInfo(MetExploreReportTableDto dto) {
        QueryWrapper<ViewExportDataColumn> wrapper = new QueryWrapper<>();
        wrapper.eq("dw_id", dto.getDwId());
        wrapper.eq("table_name", dto.getTableName());
        return viewExportDataColumnMapper.selectList(wrapper);
    }
}
