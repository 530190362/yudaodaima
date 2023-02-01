package com.bigdata.backstage.modules.dw.vo;


import cn.hutool.json.JSONArray;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class OdpsVo {
    // 数据
    private JSONArray jsonAr;
}


