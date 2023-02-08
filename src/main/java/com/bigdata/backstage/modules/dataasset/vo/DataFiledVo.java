package com.bigdata.backstage.modules.dataasset.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "数据资产字段查询", description = "数据资产字段查询")
public class DataFiledVo {

    @ApiModelProperty("字段名")
    private String colName;

    @ApiModelProperty("字段类型")
    private String colType;

    @ApiModelProperty("字段描述")
    private String colComment;
}
