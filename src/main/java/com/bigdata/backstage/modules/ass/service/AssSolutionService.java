package com.bigdata.backstage.modules.ass.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigdata.backstage.modules.ass.model.AssSolution;
import com.bigdata.backstage.modules.ass.vo.AssSolutionVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 解决方案落地经验库 服务类
 * </p>
 *
 * @author macro
 * @since 2023-01-26
 */
public interface AssSolutionService extends IService<AssSolution> {

    // 删除指定解决方案
    void delete(Integer id);

    //新增指定解决方案
    void create(AssSolutionVo assSolutionVo);

    //分页查询后台菜单
    IPage<AssSolution> selectPage(AssSolutionVo assSolutionVo, Integer pageSize, Integer pageNum);


    //修改指定解决方案
    void update(Integer id, AssSolutionVo metSolution);
}
