package com.bigdata.backstage.modules.norm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigdata.backstage.common.exception.Asserts;
import com.bigdata.backstage.modules.norm.dto.NormTableDto;
import com.bigdata.backstage.modules.norm.model.NormNode;
import com.bigdata.backstage.modules.norm.model.NormRoot;
import com.bigdata.backstage.modules.norm.model.NormTable;
import com.bigdata.backstage.modules.norm.mapper.NormTableMapper;
import com.bigdata.backstage.modules.norm.service.NormTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bigdata.backstage.modules.norm.vo.NormNodeVo;
import com.bigdata.backstage.modules.norm.vo.NormTableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 * 数据标准-表命名规范 服务实现类
 * </p>
 *
 * @author macro
 * @since 2023-02-06
 */
@Service
public class NormTableServiceImpl extends ServiceImpl<NormTableMapper, NormTable> implements NormTableService {



    //导出EXCEL数据
    @Override
    public void exportTableData(HttpServletResponse response) {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = null;
        try {
            fileName = URLEncoder.encode("表命名规范", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        List<NormTable> NormTableList = baseMapper.selectList(null);
        //NormTable -> NormTableVo
        List<NormTableVo> normTableVoList = BeanUtil.copyToList(NormTableList, NormTableVo.class);
        try {
            EasyExcel.write(response.getOutputStream(), NormTableVo.class).sheet("index")
                    .doWrite(normTableVoList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //分页模糊查询
    @Override
    public IPage<NormTable> selectPage(NormTableDto normTableDto) {
        //分页
        Integer pageNum = normTableDto.getPageNum();
        Integer pageSize = normTableDto.getPageSize();
        Page<NormTable> pageParam = new Page<>(pageNum, pageSize);

        //获取条件值
        String level = normTableDto.getLevel();
        String tableDesc = normTableDto.getTableDesc();
        QueryWrapper<NormTable> wrapper = new QueryWrapper<>();
        if (!StrUtil.isEmpty(level)) {
            wrapper.like("level", level);
        }
        if (!StrUtil.isEmpty(tableDesc)) {
            wrapper.like("table_desc", tableDesc);
        }
        //调用mapper方法实现分页条件查询
        return baseMapper.selectPage(pageParam, wrapper);
    }

    //新增
    @Override
    public void add(NormTableDto normTableDto) {
        String level  = normTableDto.getLevel();
        QueryWrapper<NormTable> wrapper = new QueryWrapper<>();
        wrapper.eq("level", level);
        Long isExist = baseMapper.selectCount(wrapper);
        if (isExist > 0) {
            Asserts.fail("此数仓层级已存在");
        }
        normTableDto.setLevel(level.trim().toUpperCase());
        NormTable normTable = BeanUtil.copyProperties(normTableDto, NormTable.class);
        baseMapper.insert(normTable);
    }
}
