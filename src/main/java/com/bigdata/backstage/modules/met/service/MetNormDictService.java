package com.bigdata.backstage.modules.met.service;

import com.bigdata.backstage.modules.met.model.MetNormDict;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * <p>
 * 数据标准-字典库 服务类
 * </p>
 *
 * @author macro
 * @since 2023-02-06
 */
public interface MetNormDictService extends IService<MetNormDict> {

    //根据数据id查询子数据列表
    List<MetNormDict> findChildData(Long id);

    //导出数据字典接口
    void exportDictData(HttpServletResponse response)  ;

    //导入数据字典接口
    void importDictData(MultipartFile file);
}
