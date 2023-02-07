package com.bigdata.backstage.modules.norm.controller;


import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据标准-任务节点 前端控制器
 * </p>
 *
 * @author macro
 * @since 2023-02-06
 */
@Api(tags = "MetNormNodeController")
@Tag(name = "MetNormNodeController", description = "数据标准-节点")
@RestController
@RequestMapping("/met/metNormNode")
public class NormNodeController {

}

