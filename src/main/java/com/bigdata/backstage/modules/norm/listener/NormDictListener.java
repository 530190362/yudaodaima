package com.bigdata.backstage.modules.norm.listener;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bigdata.backstage.modules.norm.mapper.NormDictMapper;
import com.bigdata.backstage.modules.norm.model.NormDict;
import com.bigdata.backstage.modules.norm.vo.NormDictVo;

public class NormDictListener extends AnalysisEventListener<NormDictVo> {

    private NormDictMapper normDictMapper;

    public NormDictListener(NormDictMapper normDictMapper) {
        this.normDictMapper = normDictMapper;
    }

    //一行一行读取
    @Override
    public void invoke(NormDictVo normDictVo, AnalysisContext analysisContext) {
        NormDict normDict = new NormDict();
        BeanUtil.copyProperties(normDictVo, normDict);

        QueryWrapper<NormDict> queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", normDictVo.getId());
        Long count = normDictMapper.selectCount(queryWrapper);
        if (count > 0) {
            normDictMapper.updateById(normDict);
        } else {
            normDictMapper.insert(normDict);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
