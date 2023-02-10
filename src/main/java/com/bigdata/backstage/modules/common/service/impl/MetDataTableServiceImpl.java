package com.bigdata.backstage.modules.common.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bigdata.backstage.modules.common.mapper.MetDataTableMapper;
import com.bigdata.backstage.modules.common.mapper.MetDwInfoMapper;
import com.bigdata.backstage.modules.common.mapper.ViewMetDataTableMapper;
import com.bigdata.backstage.modules.common.model.MetDataTable;
import com.bigdata.backstage.modules.common.model.MetDwInfo;
import com.bigdata.backstage.modules.common.model.ViewMetDataTable;
import com.bigdata.backstage.modules.common.service.MetDataTableService;
import com.bigdata.backstage.modules.source.dto.DataSourceHistoryDto;
import com.bigdata.backstage.modules.source.dto.DataSourceInfoDto;
import com.bigdata.backstage.modules.source.dto.DataSourcePageDto;
import com.bigdata.backstage.modules.source.dto.DataSourceTotalDto;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Value("${dw.id}")
    private Integer dwId;


    @Autowired
    private MetDwInfoMapper metDwInfoMapper;


    //同步表级别
    @Override
    public void syncTable() {
        baseMapper.syncTableInsert(dwId);
        baseMapper.syncTableUpdate();
        baseMapper.syncTableDelete();
        baseMapper.syncColumnRecover();
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
    public DataSourceTotalDto selectOdsTable() {
        return baseMapper.selectOdsIndex();
    }

    //数据集成-折线图
    @Override
    public List<DataSourceHistoryDto> selectOdsHistory(Integer limit) {
        return baseMapper.selectOdsHistory(limit);
    }

    //数据集成-表单(分页模糊查询)
    @Override
    public IPage<MetDataTable> selectOdsPage(DataSourcePageDto dto) {
        Integer pageNum = dto.getPageNum();
        Integer pageSize = dto.getPageSize();
        Page<MetDataTable> pageParam = new Page<>(pageNum, pageSize);
        QueryWrapper<MetDataTable> wrapper = new QueryWrapper<>();
        String name = dto.getTableName();
        if (!StrUtil.isEmpty(name)) {
            wrapper.like("tbl_name", name);
        }
        Integer dwId = dto.getDwId();
        String dwNameZn = "数仓合集";
        if (dwId != null) {
            wrapper.eq("dw_id", dto.getDwId());
            dwNameZn = metDwInfoMapper.selectById(dwId).getDwNameZn();
        }
        wrapper.likeRight("tbl_name", "ods_" + dto.getSourceCode()+"_");
        Page<MetDataTable> viewMetDataTablePage = baseMapper.selectPage(pageParam, wrapper);
        long size = viewMetDataTablePage.getSize();
        if (size > 0) {
            String finalDwNameZn = dwNameZn;
            viewMetDataTablePage.getRecords().forEach(item -> {
                item.setDwName(finalDwNameZn);
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
