package com.bigdata.backstage.modules.ass.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigdata.backstage.common.exception.Asserts;
import com.bigdata.backstage.modules.ass.model.AssSolution;
import com.bigdata.backstage.modules.ass.service.AssSolutionService;
import com.bigdata.backstage.modules.ass.vo.AssSolutionVo;
import com.bigdata.backstage.modules.ass.mapper.AssSolutionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 解决方案落地经验库 服务实现类
 * </p>
 *
 * @author macro
 * @since 2023-01-26
 */
@Service
public class AssSolutionServiceImpl extends ServiceImpl<AssSolutionMapper, AssSolution> implements AssSolutionService {


    @Autowired
    private AssSolutionMapper assSolutionMapper;


    //新增指定解决方案
    @Override
    public void create(AssSolutionVo assSolutionVo) {
        QueryWrapper<AssSolution> wrapper = new QueryWrapper<>();
        String solutionName = assSolutionVo.getSolutionName();
        wrapper.eq("solution_name", solutionName);
        if (assSolutionMapper.selectOne(wrapper) != null) {
            Asserts.fail("解决方案名称已存在");
        }
        AssSolution assSolution = BeanUtil.copyProperties(assSolutionVo, AssSolution.class);
        assSolutionMapper.insert(assSolution);
    }

    //删除指定解决方案
    @Override
    public void delete(Integer id) {
        assSolutionMapper.deleteById(id);
    }


    //修改指定解决方案
    @Override
    public void update(Integer id, AssSolutionVo assSolutionVo) {
        AssSolution assSolution = BeanUtil.copyProperties(assSolutionVo, AssSolution.class);
        assSolution.setId(id);
        assSolutionMapper.updateById(assSolution);
    }


    //分页查询后台菜单
    @Override
    public IPage<AssSolution> selectPage(AssSolutionVo assSolutionVo, Integer pageSize, Integer pageNum) {
        Page<AssSolution> pageParam = new Page<>(pageNum, pageSize);
        //获取条件值
        String solutionName = assSolutionVo.getSolutionName();
        String solutionDesc = assSolutionVo.getSolutionDesc();

        //封装参数
        QueryWrapper<AssSolution> wrapper = new QueryWrapper<>();
        if (!StrUtil.isEmpty(solutionName)) {
            wrapper.like("solution_name", solutionName);
        }
        if (!StrUtil.isEmpty(solutionDesc)) {
            wrapper.like("solution_desc", solutionDesc);
        }
        //调用mapper方法实现分页条件查询
        return assSolutionMapper.selectPage(pageParam, wrapper);
    }

}
