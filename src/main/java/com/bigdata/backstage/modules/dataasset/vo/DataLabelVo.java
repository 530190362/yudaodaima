package com.bigdata.backstage.modules.dataasset.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "数据资产标签查询分页展示", description = "数据资产标签查询分页展示")
public class DataLabelVo {

    private Long id;

    @ApiModelProperty("标签名称")
    private String labelName;

    @ApiModelProperty("标签备注")
    private String labelComment;

    @ApiModelProperty("创建用户")
    private String createUser;

    @ApiModelProperty("关联表数量")
    private Integer relationTblNum;

    @ApiModelProperty("创建时间")
    private Date createTime;
}
