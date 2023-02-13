package com.bigdata.backstage.modules.dataasset.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "数据资产关联标签", description = "数据资产关联标签")
public class DataAssetBindVo {

    private Long id;

    @ApiModelProperty("表名")
    private String projectName;

    @ApiModelProperty("表名")
    private String tableName;
}
