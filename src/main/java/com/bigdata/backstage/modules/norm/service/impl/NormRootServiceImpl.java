package com.bigdata.backstage.modules.norm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigdata.backstage.common.exception.Asserts;
import com.bigdata.backstage.modules.ass.model.AssSolution;
import com.bigdata.backstage.modules.norm.dto.NormRootDto;
import com.bigdata.backstage.modules.norm.model.NormDict;
import com.bigdata.backstage.modules.norm.model.NormRoot;
import com.bigdata.backstage.modules.norm.mapper.NormRootMapper;
import com.bigdata.backstage.modules.norm.service.NormRootService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据标准-字根库 服务实现类
 * </p>
 *
 * @author macro
 * @since 2023-02-06
 */
@Service
public class NormRootServiceImpl extends ServiceImpl<NormRootMapper, NormRoot> implements NormRootService {

    @Autowired
    private NormRootMapper normRootMapper;
    // 分页查询
    @Override
    public IPage<NormRoot> selectPage(NormRootDto normRootDto) {
        //分页
        Integer pageNum = normRootDto.getPageNum();
        Integer pageSize = normRootDto.getPageSize();
        Page<NormRoot> pageParam = new Page<>(pageNum, pageSize);

        //获取条件值
        String nameEn = normRootDto.getNameEn();
        String nameZh = normRootDto.getNameZh();
        String rootDesc = normRootDto.getRootDesc();
        QueryWrapper<NormRoot> wrapper = new QueryWrapper<>();
        if (!StrUtil.isEmpty(nameEn)) {
            wrapper.like("name_en", nameEn);
        }
        if (!StrUtil.isEmpty(nameZh)) {
            wrapper.like("name_zh", nameZh);
        }
        if (!StrUtil.isEmpty(rootDesc)) {
            wrapper.like("root_desc", rootDesc);
        }
        wrapper.orderByDesc("update_time");
        //调用mapper方法实现分页条件查询
        return normRootMapper.selectPage(pageParam, wrapper);
    }

    //新增
    @Override
    public void add(NormRootDto normRootDto) {
        String nameShortEn = normRootDto.getNameShortEn();
        QueryWrapper<NormRoot> wrapper = new QueryWrapper<>();
        wrapper.eq("name_short_en", nameShortEn);
        Long isExist = normRootMapper.selectCount(wrapper);
        if (isExist > 0) {
            Asserts.fail("该字根已存在");
        }
        NormRoot normRoot = BeanUtil.copyProperties(normRootDto, NormRoot.class);
        normRootMapper.insert(normRoot);
    }


}
