package com.bigdata.backstage.modules.dataasset.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "数据资产标签查询list", description = "数据资产标签查询list")
public class UnionTblVo {

    @ApiModelProperty("标签名称")
    private String labelName;

    @ApiModelProperty("绑定数量")
    private Integer bindNum;

    @ApiModelProperty("已绑定表")
    private List<DataAssetBindVo> hasBindTblList;

    @ApiModelProperty("可绑定表")
    private List<DataAssetBindVo> ableBindTblList;
}
