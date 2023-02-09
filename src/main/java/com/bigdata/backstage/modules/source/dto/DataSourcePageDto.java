package com.bigdata.backstage.modules.source.dto;


import com.bigdata.backstage.domain.PageDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DataSourcePageDto  extends PageDto {

    @ApiModelProperty("表名")
    private String tableName;

    @ApiModelProperty("来源 irs/bms")
    private String sourceType;
}
