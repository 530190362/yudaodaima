package com.bigdata.backstage.modules.common.mapper;

import com.bigdata.backstage.modules.common.model.ViewMetDetailOutline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bigdata.backstage.modules.dataasset.vo.DataFiledVo;
import com.bigdata.backstage.modules.homepage.vo.DataChangeVo;
import com.bigdata.backstage.modules.homepage.vo.DataCountTop;
import com.bigdata.backstage.modules.homepage.vo.DataOverviewVo;
import com.bigdata.backstage.modules.homepage.vo.DataSizeTop;
import org.apache.ibatis.annotations.Param;
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
     *
     * @return
     */
    DataOverviewVo getOverview(@Param("dwId") Long dwId);

    /**
     * 数据资产（记录数）变化
     *
     * @param days
     * @return
     */
    List<DataChangeVo> getDataChangeNum(@Param("days") Integer days, @Param("dwId") Integer dwId);

    /**
     * 数据资产（占用空间）变化
     *
     * @param days
     * @return
     */
    List<DataChangeVo> getDataChangeSize(@Param("days") Integer days, @Param("dwId") Integer dwId);

    /**
     * 数据榜单-占用空间
     *
     * @return
     */
    List<DataSizeTop> getDataSizeTop(@Param("dwId") Integer dwId);

    /**
     * 数据榜单-总记录数
     *
     * @return
     */
    List<DataCountTop> getDataCountTop(@Param("dwId") Integer dwId);

    /**
     * 数据榜单-最近新增
     *
     * @return
     */
    List<DataCountTop> getDataNewTop(@Param("dwId") Integer dwId);

    /**
     * 数据榜单-新增记录数
     *
     * @return
     */
    List<DataCountTop> getDataNewRowTop(@Param("dwId") Integer dwId);


    List<DataFiledVo> queryDataField(@Param("tblName") String tblName);

}
