package com.bigdata.backstage.modules.met.listener;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bigdata.backstage.modules.met.mapper.MetNormDictMapper;
import com.bigdata.backstage.modules.met.model.MetNormDict;
import com.bigdata.backstage.modules.met.vo.MetNormDictVo;

public class MetNormDictListener extends AnalysisEventListener<MetNormDictVo> {

    private MetNormDictMapper metNormDictMapper;

    public MetNormDictListener(MetNormDictMapper metNormDictMapper) {
        this.metNormDictMapper = metNormDictMapper;
    }

    //一行一行读取
    @Override
    public void invoke(MetNormDictVo metNormDictVo, AnalysisContext analysisContext) {
        MetNormDict metNormDict = new MetNormDict();
        BeanUtil.copyProperties(metNormDictVo, metNormDict);

        QueryWrapper<MetNormDict> queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", metNormDictVo.getId());
        Long count = metNormDictMapper.selectCount(queryWrapper);
        if (count > 0) {
            metNormDictMapper.updateById(metNormDict);
        } else {
            metNormDictMapper.insert(metNormDict);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
