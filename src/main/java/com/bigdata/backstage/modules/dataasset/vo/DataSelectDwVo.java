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
public class DataSelectDwVo {

    private Integer id;

    @ApiModelProperty("项目英文名")
    private String dwNameEn;

    @ApiModelProperty("项目中文名")
    private String dwNameZn;
}
