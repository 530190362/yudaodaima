package com.bigdata.backstage.modules.common.mapper;

import com.bigdata.backstage.modules.common.model.ViewMetDetailOutline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bigdata.backstage.modules.homepage.vo.DataChangeVo;
import com.bigdata.backstage.modules.homepage.vo.DataCountTop;
import com.bigdata.backstage.modules.homepage.vo.DataOverviewVo;
import com.bigdata.backstage.modules.homepage.vo.DataSizeTop;
import org.apache.ibatis.annotations.Param;
import org.aspectj.weaver.ast.Var;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * VIEW Mapper 接口
 * </p>
 *
 * @author macro
 * @since 2023-02-06
 */
@Repository
public interface ViewMetDetailOutlineMapper extends BaseMapper<ViewMetDetailOutline> {

    /**
     * 首页概览-数据统计
     * @return
     */
    DataOverviewVo getOverview();

    /**
     * 数据资产（记录数）变化
     * @param days
     * @return
     */
    List<DataChangeVo> getDataChangeNum(@Param("days") Integer days);

    /**
     * 数据资产（占用空间）变化
     * @param days
     * @return
     */
    List<DataChangeVo> getDataChangeSize(@Param("days") Integer days);

    /**
     * 数据榜单-占用空间
     * @return
     */
    List<DataSizeTop> getDataSizeTop();

    /**
     * 数据榜单-总记录数
     * @return
     */
    List<DataCountTop> getDataCountTop();
}
