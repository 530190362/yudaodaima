package com.bigdata.backstage.modules.ass.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;



@Data
@EqualsAndHashCode(callSuper = false)
public class AssSolutionVo {

    // 解决方案名称
    private String solutionName;

    // 解决方案描述
    private String solutionDesc;

    // 解决方案钉钉URL
    private String solutionDingdingUrl;

    // 解决方案GitLabURL
    private String solutionGitlabUrl;

}
