package com.bigdata.backstage.modules.dataasset.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "数据资产表名查询list", description = "数据资产表名查询list")
public class DataSelectAssetVo {

    private Long id;

    @ApiModelProperty("表名")
    private String tableName;
}
