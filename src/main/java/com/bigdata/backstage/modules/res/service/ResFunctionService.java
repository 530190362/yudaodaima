package com.bigdata.backstage.modules.res.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigdata.backstage.modules.res.dto.ResFunctionDto;
import com.bigdata.backstage.modules.res.model.ResFunction;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 资源管理-UDF函数管理 服务类
 * </p>
 *
 * @author bigdata
 * @since 2023-02-07
 */
public interface ResFunctionService extends IService<ResFunction> {

    //导出EXCEL数据
    void exportFunctionData(HttpServletResponse response);

    //分页模糊查询
    IPage<ResFunction> selectPage(ResFunctionDto resFunctionDto);

    //resFunctionDto
    void add(ResFunctionDto resFunctionDto);
}
