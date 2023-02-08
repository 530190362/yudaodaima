package com.bigdata.backstage.modules.res.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigdata.backstage.common.exception.Asserts;
import com.bigdata.backstage.modules.norm.model.NormRoot;
import com.bigdata.backstage.modules.norm.model.NormTable;
import com.bigdata.backstage.modules.norm.vo.NormTableVo;
import com.bigdata.backstage.modules.res.dto.ResFunctionDto;
import com.bigdata.backstage.modules.res.model.ResFunction;
import com.bigdata.backstage.modules.res.mapper.ResFunctionMapper;
import com.bigdata.backstage.modules.res.service.ResFunctionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bigdata.backstage.modules.res.vo.ResFunctionVo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 * 资源管理-UDF函数管理 服务实现类
 * </p>
 *
 * @author bigdata
 * @since 2023-02-07
 */
@Service
public class ResFunctionServiceImpl extends ServiceImpl<ResFunctionMapper, ResFunction> implements ResFunctionService {


    //导出EXCEL数据
    @Override
    public void exportFunctionData(HttpServletResponse response) {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = null;
        try {
            fileName = URLEncoder.encode("函数合集", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        List<ResFunction> ResFunctionList = baseMapper.selectList(null);
        //ResFunction -> ResFunctionVo
        List<ResFunctionVo> ResFunctionVoList = BeanUtil.copyToList(ResFunctionList, ResFunctionVo.class);
        try {
            EasyExcel.write(response.getOutputStream(), ResFunctionVo.class).sheet("index")
                    .doWrite(ResFunctionVoList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    ///分页模糊查询
    @Override
    public IPage<ResFunction> selectPage(ResFunctionDto resFunctionDto) {
        //分页
        Integer pageNum = resFunctionDto.getPageNum();
        Integer pageSize = resFunctionDto.getPageSize();
        Page<ResFunction> pageParam = new Page<>(pageNum, pageSize);

        //获取条件值
        String name = resFunctionDto.getName();
        String functionDesc = resFunctionDto.getFunctionDesc();
        QueryWrapper<ResFunction> wrapper = new QueryWrapper<>();
        if (!StrUtil.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StrUtil.isEmpty(functionDesc)) {
            wrapper.like("function_desc", functionDesc);
        }

        //调用mapper方法实现分页条件查询
        return baseMapper.selectPage(pageParam, wrapper);

    }

    //resFunctionDto
    @Override
    public void add(ResFunctionDto resFunctionDto) {
        String name = resFunctionDto.getName().trim().toUpperCase();
        QueryWrapper<ResFunction> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        Long isExist = baseMapper.selectCount(wrapper);
        if (isExist > 0) {
            Asserts.fail("此函数名称已存在");
        }
        resFunctionDto.setName(name);
        ResFunction resFunction = BeanUtil.copyProperties(resFunctionDto, ResFunction.class);
        baseMapper.insert(resFunction);

    }
}
