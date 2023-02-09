package com.bigdata.backstage.modules.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author bigdata
 * @since 2023-02-09
 */
@Getter
@Setter
@TableName("view_met_data_table")
@ApiModel(value = "ViewMetDataTable对象", description = "VIEW")
public class ViewMetDataTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private String dwName;

    @TableField(exist = false)
    private String SourceType;

    private String tblName;

    private String tblLevel;

    private String tblComment;

    private String tblCreateTime;

    private String tblUpdateTime;

    private String isPt;

    private String ptLast;

    private String tblLifecycle;

    private String tblType;

    private String tblSize;

    private String tblColNum;

    private String allRowCount;

    private String rowCount;


}
