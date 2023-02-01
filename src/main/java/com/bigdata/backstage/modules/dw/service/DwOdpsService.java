package com.bigdata.backstage.modules.dw.service;

import cn.hutool.json.JSONArray;

public interface DwOdpsService {

    //JSON格式转换
    String jsonConvert(JSONArray sourceData);
}
