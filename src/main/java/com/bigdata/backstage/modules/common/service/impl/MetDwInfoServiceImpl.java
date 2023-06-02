package com.bigdata.backstage.modules.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bigdata.backstage.modules.common.mapper.MetDwTableMapInfoMapper;
import com.bigdata.backstage.modules.common.model.MetDwInfo;
import com.bigdata.backstage.modules.common.mapper.MetDwInfoMapper;
import com.bigdata.backstage.modules.common.model.MetDwTableMapInfo;
import com.bigdata.backstage.modules.common.service.MetDwInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数仓主表 服务实现类
 * </p>
 *
 * @author macro
 * @since 2023-02-08
 */
@Service
public class MetDwInfoServiceImpl extends ServiceImpl<MetDwInfoMapper, MetDwInfo> implements MetDwInfoService {

    @Autowired
    private MetDwInfoMapper metDwInfoMapper;

    @Autowired
    private MetDwTableMapInfoMapper metDwTableMapInfoMapper;

    /**
     * @param dwNameEn 数仓英文名/用户名名
     * @return 返回《数仓信息》对象
     */
    @Override
    public MetDwInfo getDwInfo(String dwNameEn) {
        QueryWrapper<MetDwInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("dw_name_en", dwNameEn);
        return metDwInfoMapper.selectOne(wrapper);
    }


    /**
     * @param dwId 数仓ID
     * @return 返回《数仓映射表》对象
     */
    @Override
    public MetDwTableMapInfo getDwMapInfo(int dwId) {
        QueryWrapper<MetDwTableMapInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("dw_id", dwId);
        return metDwTableMapInfoMapper.selectOne(wrapper);
    }

}
