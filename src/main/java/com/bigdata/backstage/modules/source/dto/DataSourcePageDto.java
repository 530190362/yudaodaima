package com.bigdata.backstage.modules.source.dto;


import com.bigdata.backstage.domain.PageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "数据集成-模糊查询分页", description = "DataSourcePageDto传输对象")
public class DataSourcePageDto  extends PageDto {

    @ApiModelProperty("数仓id")
    private Integer dwId;

    @ApiModelProperty("表名")
    private String tableName;

    @ApiModelProperty("来源 irs/bms")
    private String sourceCode;

    @ApiModelProperty("来源 irs/bms")
    private String sourceName;

}
