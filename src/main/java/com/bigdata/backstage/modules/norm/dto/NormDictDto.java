package com.bigdata.backstage.modules.norm.dto;


import com.bigdata.backstage.domain.PageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "NormDictDto传输对象", description = "数据标准-字典库")
public class NormDictDto extends PageDto {

    @ApiModelProperty(value = "父ID")
    private Long parentId;

    //字典编码
    @ApiModelProperty(value = "字典编码")
    private String nameCode;

    //字典值
    @ApiModelProperty(value = "字典值")
    private String name;

    //字典描述
    @ApiModelProperty(value = "字典描述")
    private String dictDesc;

    //创建人
    @ApiModelProperty(value = "创建人")
    private String createUser;

    //更新人
    @ApiModelProperty(value = "更新人")
    private String updateUser;

}
