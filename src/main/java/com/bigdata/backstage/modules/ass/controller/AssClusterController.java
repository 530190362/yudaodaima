package com.bigdata.backstage.modules.ass.controller;

import com.bigdata.backstage.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Api(tags = "AssClusterController")
@Tag(name = "AssClusterController", description = "集群信息")
@RestController
@RequestMapping("/ass/cluster")
public class AssClusterController {

    @Value("${cluster.hadoop}")
    private String hadoopUrl;

    @Value("${cluster.neo4j}")
    private String neo4jUrl;

    @Value("${cluster.docker}")
    private String dockerUrl;

    @ApiOperation("返回集群URL")
    @GetMapping(value = "/list")
    public CommonResult getClusterList() {
        HashMap<Object, Object> clusterMap = new HashMap<>();
        clusterMap.put("hadoopUrl",hadoopUrl);
        clusterMap.put("neo4jUrl",neo4jUrl);
        clusterMap.put("dockerUrl",dockerUrl);
        return CommonResult.success(clusterMap);
    }
}
