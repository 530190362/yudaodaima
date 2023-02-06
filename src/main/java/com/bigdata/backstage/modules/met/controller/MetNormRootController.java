package com.bigdata.backstage.modules.met.controller;


import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据标准-字根库 前端控制器
 * </p>
 *
 * @author macro
 * @since 2023-02-06
 */
@Api(tags = "MetNormRootController")
@Tag(name = "MetNormRootController", description = "数据标准-词根库")
@RestController
@RequestMapping("/met/metNormRoot")
public class MetNormRootController {


}

