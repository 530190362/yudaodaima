package com.bigdata.backstage.modules.norm.controller;


import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据标准-表命名规范 前端控制器
 * </p>
 *
 * @author macro
 * @since 2023-02-06
 */
@Api(tags = "MetNormTableController")
@Tag(name = "MetNormTableController", description = "数据标准-表规范")
@RestController
@RequestMapping("/met/metNormTable")
public class NormTableController {


}

