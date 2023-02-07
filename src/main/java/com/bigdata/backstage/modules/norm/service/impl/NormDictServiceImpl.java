package com.bigdata.backstage.modules.norm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigdata.backstage.modules.norm.dto.NormDictDto;
import com.bigdata.backstage.modules.norm.listener.NormDictListener;

import com.bigdata.backstage.modules.norm.vo.NormDictVo;
import com.bigdata.backstage.modules.norm.model.NormDict;
import com.bigdata.backstage.modules.norm.mapper.NormDictMapper;
import com.bigdata.backstage.modules.norm.service.NormDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
public class NormDictServiceImpl extends ServiceImpl<NormDictMapper, NormDict> implements NormDictService {


    @Autowired
    private NormDictMapper normDictMapper;

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
        QueryWrapper<NormDict> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1);
        List<NormDict> dictList = baseMapper.selectList(wrapper);
        //dict -> dictVo
        List<NormDictVo> dictVoList = BeanUtil.copyToList(dictList, NormDictVo.class);
        try {
            EasyExcel.write(response.getOutputStream(), NormDictVo.class).sheet("dict")
                    .doWrite(dictVoList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //导入数据字典接口
    @Override
    @CacheEvict(value = "bigdata:dict", allEntries = true)
    public void importDictData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), NormDictVo.class, new NormDictListener(baseMapper)).sheet().doRead();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //分页模糊查询
    @Override
    public IPage<NormDict> selectPage(NormDictDto normDictDto, Integer pageSize, Integer pageNum) {
        Long parentId = normDictDto.getParentId();
        String name = normDictDto.getName();
        String nameCode = normDictDto.getNameCode();
        //封装参数
        Page<NormDict> pageParam = new Page<>(pageNum, pageSize);
        QueryWrapper<NormDict> wrapper = new QueryWrapper<>();
        if (parentId != null) {
            wrapper.eq("parent_id", parentId);
        }
        if (!StrUtil.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StrUtil.isEmpty(nameCode)) {
            wrapper.like("name_code", nameCode);
        }
        return normDictMapper.selectPage(pageParam, wrapper);

    }

    //根据数据id查询子数据列表
    @Override
    @Cacheable(value = "bigdata:dict", keyGenerator = "keyGenerator")
    public List<NormDict> findChildData(Long id) {
        QueryWrapper<NormDict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        List<NormDict> dictList = baseMapper.selectList(wrapper);
        for (NormDict dict : dictList) {
            Long dictId = dict.getId();
            //判断是否有子节点，如果有则继续查询子节点
            boolean isChild = this.isChildren(dictId);
            dict.setHasChildren(isChild);
        }

        return dictList;
    }

    //判断id下面是否有子节点
    private boolean isChildren(Long id) {
        QueryWrapper<NormDict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        Long count = baseMapper.selectCount(wrapper);
        return count > 0;
    }
}
