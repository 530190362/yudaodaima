package com.bigdata.backstage.modules.common.mapper;

import com.bigdata.backstage.modules.common.model.MetDwInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bigdata.backstage.modules.dataasset.vo.DataSelectDwVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 数仓主表 Mapper 接口
 * </p>
 *
 * @author macro
 * @since 2023-02-08
 */
@Repository
public interface MetDwInfoMapper extends BaseMapper<MetDwInfo> {

    List<DataSelectDwVo> queryDataSelectDw();
}
