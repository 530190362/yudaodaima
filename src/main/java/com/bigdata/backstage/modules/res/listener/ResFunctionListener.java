package com.bigdata.backstage.modules.res.listener;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.bigdata.backstage.modules.res.vo.ResFunctionVo;

public class ResFunctionListener extends AnalysisEventListener<ResFunctionVo> {
    //一行一行读取
    @Override
    public void invoke(ResFunctionVo resFunctionVo, AnalysisContext analysisContext) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
