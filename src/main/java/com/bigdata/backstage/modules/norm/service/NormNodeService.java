package com.bigdata.backstage.modules.norm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigdata.backstage.modules.norm.dto.NormNodeDto;
import com.bigdata.backstage.modules.norm.model.NormNode;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 数据标准-任务节点 服务类
 * </p>
 *
 * @author macro
 * @since 2023-02-06
 */
public interface NormNodeService extends IService<NormNode> {

    //分页查询
    IPage<NormNode> selectPage(NormNodeDto normNodeDto, Integer pageSize, Integer pageNum);

    ////导出为EXCEL(全量)
    void exportNodeData(HttpServletResponse response);
}
