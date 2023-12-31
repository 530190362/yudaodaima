package com.bigdata.backstage.modules.norm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigdata.backstage.modules.norm.dto.NormNodeDto;
import com.bigdata.backstage.modules.norm.model.NormNode;
import com.bigdata.backstage.modules.norm.mapper.NormNodeMapper;
import com.bigdata.backstage.modules.norm.service.NormNodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bigdata.backstage.modules.norm.vo.NormNodeVo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 * 数据标准-任务节点 服务实现类
 * </p>
 *
 * @author bigdata
 * @since 2023-02-06
 */
@Service
public class NormNodeServiceImpl extends ServiceImpl<NormNodeMapper, NormNode> implements NormNodeService {



    //分页查询
    @Override
    public IPage<NormNode> selectPage(NormNodeDto normNodeDto) {
        //分页参数数
        Integer pageNum = normNodeDto.getPageNum();
        Integer pageSize = normNodeDto.getPageSize();
        Page<NormNode> pageParam = new Page<>(pageNum, pageSize);

        //where条件
        String nodeDesc = normNodeDto.getNodeDesc();
        String name = normNodeDto.getName();
        QueryWrapper<NormNode> wrapper = new QueryWrapper<>();
        if (!StrUtil.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StrUtil.isEmpty(nodeDesc)) {
            wrapper.like("node_desc", nodeDesc);
        }
        return baseMapper.selectPage(pageParam, wrapper);
    }

    ////导出为EXCEL(全量)
    @Override
    public void exportNodeData(HttpServletResponse response) {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = null;
        try {
            fileName = URLEncoder.encode("任务节点", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        List<NormNode> NormNodeList = baseMapper.selectList(null);
        //NormNode -> NormNodeVo
        List<NormNodeVo> normNodeVoList = BeanUtil.copyToList(NormNodeList, NormNodeVo.class);
        try {
            EasyExcel.write(response.getOutputStream(), NormNodeVo.class).sheet("node")
                    .doWrite(normNodeVoList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
