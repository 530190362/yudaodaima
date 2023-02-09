package com.bigdata.backstage.modules.common.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bigdata.backstage.modules.common.mapper.MetDataTableMapper;
import com.bigdata.backstage.modules.common.model.MetDataTable;
import com.bigdata.backstage.modules.common.service.MetDataTableService;
import com.bigdata.backstage.modules.norm.model.NormNode;
import com.bigdata.backstage.modules.source.dto.DataSourceHistoryDto;
import com.bigdata.backstage.modules.source.dto.DataSourcePageDto;
import com.bigdata.backstage.modules.source.dto.DataSourceTotalDto;
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

    @Value("${dw.id}")
    private Integer dwId;


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
        DataSourceTotalDto dataSourceTotalDto =baseMapper.selectOdsIndex();
        return dataSourceTotalDto;
    }

    //数据集成-折线图
    @Override
    public List<DataSourceHistoryDto> selectOdsHistory(Integer limit) {
        List<DataSourceHistoryDto> resultList = baseMapper.selectOdsHistory(limit);
        return resultList;
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
        return baseMapper.selectPage(pageParam, wrapper);
    }
}
