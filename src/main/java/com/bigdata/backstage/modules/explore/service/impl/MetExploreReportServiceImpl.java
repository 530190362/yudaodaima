package com.bigdata.backstage.modules.explore.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigdata.backstage.modules.common.model.MetDwInfo;
import com.bigdata.backstage.modules.explore.dto.MetExploreReportDto;
import com.bigdata.backstage.modules.explore.dto.MetExploreReportTableDto;
import com.bigdata.backstage.modules.explore.mapper.ViewExploreDataColumnMapper;
import com.bigdata.backstage.modules.explore.mapper.ViewExploreDataTableMapper;
import com.bigdata.backstage.modules.explore.model.MetExploreReport;
import com.bigdata.backstage.modules.explore.mapper.MetExploreReportMapper;
import com.bigdata.backstage.modules.explore.model.ViewExploreDataColumn;
import com.bigdata.backstage.modules.explore.model.ViewExploreDataTable;
import com.bigdata.backstage.modules.explore.service.MetExploreReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    private ViewExploreDataTableMapper viewExploreDataTableMapper;

    @Autowired
    private ViewExploreDataColumnMapper viewExploreDataColumnMapper;

    //勘探报告模糊查询
    @Override
    public IPage<ViewExploreDataTable> selectPage(MetExploreReportDto dto) {
        //分页查询
        Integer pageNum = dto.getPageNum();
        Integer pageSize = dto.getPageSize();
        Page<ViewExploreDataTable> pageParam = new Page<>(pageNum, pageSize);

        //分页多表关联模糊查询
        MPJLambdaWrapper<ViewExploreDataTable> joinMrapper = new MPJLambdaWrapper<>();
        joinMrapper.selectAll(ViewExploreDataTable.class)
                .selectAs(MetDwInfo::getDwNameZn, ViewExploreDataTable::getDwName)
                .leftJoin(MetDwInfo.class, MetDwInfo::getId, ViewExploreDataTable::getDwId);

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
        return viewExploreDataTableMapper.selectPage(pageParam, joinMrapper);

    }

    //勘探报告表明确信息
    @Override
    public ViewExploreDataTable exploreTablInfo(MetExploreReportTableDto dto) {
        QueryWrapper<ViewExploreDataTable> wrapper = new QueryWrapper<>();
        wrapper.eq("dw_id", dto.getDwId());
        wrapper.eq("table_name", dto.getTableName());
        return viewExploreDataTableMapper.selectOne(wrapper);
    }

    //勘探报告字段明确信息
    @Override
    public List<ViewExploreDataColumn> exploreColumnsInfo(MetExploreReportTableDto dto) {
        QueryWrapper<ViewExploreDataColumn> wrapper = new QueryWrapper<>();
        wrapper.eq("dw_id", dto.getDwId());
        wrapper.eq("table_name", dto.getTableName());
        return viewExploreDataColumnMapper.selectList(wrapper);
    }
}
