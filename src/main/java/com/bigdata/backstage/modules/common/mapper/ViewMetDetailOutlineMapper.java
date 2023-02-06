package com.bigdata.backstage.modules.common.mapper;

import com.bigdata.backstage.modules.common.model.ViewMetDetailOutline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bigdata.backstage.modules.homepage.vo.DataChangeVo;
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

    DataOverviewVo getOverview();

    List<DataChangeVo> getDataChangeNum(@Param("days") Integer days);

    List<DataChangeVo> getDataChangeSize(@Param("days") Integer days);

    List<DataSizeTop> getDataSizeTop();
}
