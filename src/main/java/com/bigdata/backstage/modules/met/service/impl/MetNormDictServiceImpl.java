package com.bigdata.backstage.modules.met.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bigdata.backstage.modules.met.listener.MetNormDictListener;
import com.bigdata.backstage.modules.met.vo.MetNormDictVo;
import com.bigdata.backstage.modules.met.model.MetNormDict;
import com.bigdata.backstage.modules.met.mapper.MetNormDictMapper;
import com.bigdata.backstage.modules.met.service.MetNormDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.deploy.net.URLEncoder;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * <p>
 * 数据标准-字典库 服务实现类
 * </p>
 *
 * @author macro
 * @since 2023-02-06
 */
@Service
public class MetNormDictServiceImpl extends ServiceImpl<MetNormDictMapper, MetNormDict> implements MetNormDictService {


    //导出数据字典接口
    @Override
    public void exportDictData(HttpServletResponse response) {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = null;
        try {
            fileName = URLEncoder.encode("数据字典", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        QueryWrapper<MetNormDict> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1);
        List<MetNormDict> dictList = baseMapper.selectList(wrapper);
        //dict -> dictVo
        List<MetNormDictVo> dictVoList = BeanUtil.copyToList(dictList, MetNormDictVo.class);
        try {
            EasyExcel.write(response.getOutputStream(), MetNormDictVo.class).sheet("dict")
                    .doWrite(dictVoList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //导入数据字典接口
    @Override
    @CacheEvict(value = "dict", allEntries = true)
    public void importDictData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), MetNormDictVo.class, new MetNormDictListener(baseMapper)).sheet().doRead();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //根据数据id查询子数据列表
    @Override
    @Cacheable(value = "dict", keyGenerator = "keyGenerator")
    public List<MetNormDict> findChildData(Long id) {
        QueryWrapper<MetNormDict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        List<MetNormDict> dictList = baseMapper.selectList(wrapper);
        for (MetNormDict dict : dictList) {
            Long dictId = dict.getId();
            //判断是否有子节点，如果有则继续查询子节点
            boolean isChild = this.isChildren(dictId);
            dict.setHasChildren(isChild);
        }

        return dictList;
    }

    //判断id下面是否有子节点
    private boolean isChildren(Long id) {
        QueryWrapper<MetNormDict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        Long count = baseMapper.selectCount(wrapper);
        return count > 0;
    }
}
