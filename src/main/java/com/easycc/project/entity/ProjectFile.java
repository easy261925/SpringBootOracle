package com.easycc.project.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.easycc.project.entity.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author cc
 * @since 2021-01-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("PROJECT_FILE")
@ApiModel(value = "ProjectFile对象", description = "")
@KeySequence(value = "SEQ_PROJECT_FILE", clazz = Integer.class)
public class ProjectFile extends Base {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文件磁盘路径")
    @TableField("FILE_PATH")
    private String filePath;

    @ApiModelProperty(value = "文件访问路径")
    @TableField("URL")
    private String url;

    @ApiModelProperty(value = "文件名称")
    @TableField("FILENAME")
    private String filename;

    @ApiModelProperty(value = "文件类型")
    @TableField("TYPE")
    private String type;

    @ApiModelProperty(value = "外键ID")
    @TableField("PK_VALUE")
    private String pkValue;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "上传者 ID")
    @TableField("HANDLER_ID")
    private Long handlerId;

}
