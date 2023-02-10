package com.bigdata.backstage.modules.source.dto;


import com.bigdata.backstage.modules.common.model.MetDataColumn;
import com.bigdata.backstage.modules.common.model.MetDataTable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "数据集成-表/字段信息", description = "DataSourceInfoDto传输对象")
public class DataSourceInfoDto {

    @ApiModelProperty("表信息")
    private MetDataTable tableInfo;

    @ApiModelProperty("列信息")
    private List<MetDataColumn> columnInfo;

}
