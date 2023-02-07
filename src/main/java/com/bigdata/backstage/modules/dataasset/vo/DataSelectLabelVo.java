package com.bigdata.backstage.modules.dataasset.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "数据资产标签查询list", description = "数据资产标签查询list")
public class DataSelectLabelVo {

    private Long id;

    @ApiModelProperty("标签名称")
    private String labelName;
}


