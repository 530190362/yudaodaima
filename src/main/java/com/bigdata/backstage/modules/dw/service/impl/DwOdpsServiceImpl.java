package com.bigdata.backstage.modules.dw.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.bigdata.backstage.common.exception.Asserts;
import com.bigdata.backstage.modules.dw.service.DwOdpsService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

import static com.bigdata.backstage.common.utils.toCsvUtil.toCSV;


@Service
public class DwOdpsServiceImpl implements DwOdpsService {

    //JSON格式转换
    @Override
    public String jsonConvert(JSONArray sourceData) {
        System.out.println(sourceData);
        // LinkedHashMap 保证顺序
        List<LinkedHashMap> mapList = JSONUtil.toList(sourceData, LinkedHashMap.class);
        try {
            return toCSV(mapList);
        } catch (Exception e) {
            System.out.println(e);
            Asserts.fail("数据格式错误");
        }
        return null;
    }
}