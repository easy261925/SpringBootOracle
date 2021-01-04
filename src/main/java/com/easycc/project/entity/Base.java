package com.easycc.project.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author cc
 * @date 2021-01-04-上午11:41
 */
@Data
public class Base implements java.io.Serializable {
    @ApiModelProperty(value = "主键 ID")
    private Integer id;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "是否停用")
    private Integer ban;

    @ApiModelProperty(value = "是否删除")
    private Integer deleted;

    @ApiModelProperty(value = "版本号")
    private Integer version;
}
