package com.bigdata.backstage.common.utils;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Component
public class toCsvUtil {
    public static String toCSV(List<LinkedHashMap> list) {
        List<String> headers = (List<String>) list.stream().flatMap(map -> map.keySet().stream()).distinct().collect(toList());
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < headers.size(); i++) {
            sb.append(headers.get(i));
            sb.append(i == headers.size()-1 ? "\n" : "\t");
        }
        for (Map<String, Object> map : list) {
            for (int i = 0; i < headers.size(); i++) {
                sb.append(map.get(headers.get(i)));
                sb.append(i == headers.size()-1 ? "\n" : "\t");
            }
        }
        return sb.toString();
    }
}
