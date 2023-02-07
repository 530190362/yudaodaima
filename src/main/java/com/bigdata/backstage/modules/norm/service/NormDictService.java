package com.bigdata.backstage.modules.norm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigdata.backstage.modules.norm.dto.NormDictDto;
import com.bigdata.backstage.modules.norm.model.NormDict;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 数据标准-字典库 服务类
 * </p>
 *
 * @author macro
 * @since 2023-02-06
 */
public interface NormDictService extends IService<NormDict> {

    //根据数据id查询子数据列表
    List<NormDict> findChildData(Long id);

    //导出数据字典接口
    void exportDictData(HttpServletResponse response)  ;

    //导入数据字典接口
    void importDictData(MultipartFile file);

    //分页模糊查询
    IPage<NormDict> selectPage(NormDictDto normDictDto, Integer pageSize, Integer pageNum);
}
