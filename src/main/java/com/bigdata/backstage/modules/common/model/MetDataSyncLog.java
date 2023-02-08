package com.bigdata.backstage.modules.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 数据资产-数据同步日志表
 * </p>
 *
 * @author bigdata
 * @since 2023-02-08
 */
@Getter
@Setter
@TableName("met_data_sync_log")
@ApiModel(value = "MetDataSyncLog对象", description = "数据资产-数据同步日志表")
public class MetDataSyncLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("同步名称")
    private String name;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("开始时间")
    private Date endTime;

    @ApiModelProperty("花费时间单位 : 秒")
    private Integer spendTimes;

    @ApiModelProperty("写入时间")
    private Date createTime;


}
