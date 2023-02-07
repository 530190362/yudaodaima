package com.bigdata.backstage.modules.norm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigdata.backstage.modules.norm.dto.NormTableDto;
import com.bigdata.backstage.modules.norm.model.NormTable;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 数据标准-表命名规范 服务类
 * </p>
 *
 * @author macro
 * @since 2023-02-06
 */
public interface NormTableService extends IService<NormTable> {

    // //导出EXCEL数据
    void exportTableData(HttpServletResponse response);

    ////分页模糊查询
    IPage<NormTable> selectPage(NormTableDto normTableDto);

    // //新增
    void add(NormTableDto normTableDto);
}
