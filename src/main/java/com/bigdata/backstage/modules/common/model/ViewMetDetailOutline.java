package com.bigdata.backstage.modules.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author macro
 * @since 2023-02-06
 */
@Getter
@Setter
@TableName("view_met_detail_outline")
@ApiModel(value = "ViewMetDetailOutline对象", description = "VIEW")
public class ViewMetDetailOutline implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tblLevel;

    private String tblName;

    private String tblComment;

    private String colName;

    private String colType;

    private String colComment;

    private String tblColNum;

    private Double allRowCount;

    private Double rowCount;

    private String index1Name;

    private String index2Name;

    private String index3Name;

    private String isPt;

    private String ptLast;

    private String tblCreateTime;

    private String tblUpdateTime;

    private String tblLifecycle;

    private String tblType;

    private String tblSize;

    private String etlTm;

    private String dt;


}
