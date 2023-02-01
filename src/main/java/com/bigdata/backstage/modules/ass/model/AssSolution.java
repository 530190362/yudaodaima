package com.bigdata.backstage.modules.ass.model;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 解决方案落地经验库
 * </p>
 *
 * @author macro
 * @since 2023-01-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ass_solution_exp")
@ApiModel(value = "AssProjectSolutionExp对象", description = "解决方案落地经验库表")
public class AssSolution implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("解决方案名称")
    private String solutionName;

    @ApiModelProperty("解决方案描述")
    private String solutionDesc;

    @ApiModelProperty("解决方案钉钉URL")
    private String solutionDingdingUrl;

    @ApiModelProperty("解决方案GitLabURL")
    private String solutionGitlabUrl;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("是否删除 1-是 0-否")
    @TableLogic
    private Boolean isDeleted;


}
