package com.bigdata.backstage.modules.norm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigdata.backstage.modules.norm.dto.NormRootDto;
import com.bigdata.backstage.modules.norm.model.NormRoot;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 数据标准-字根库 服务类
 * </p>
 *
 * @author macro
 * @since 2023-02-06
 */
public interface NormRootService extends IService<NormRoot> {

    ////分页查询
    IPage<NormRoot> selectPage(NormRootDto normRootDto);

    //新增
    void add(NormRootDto normRootDto);
}
