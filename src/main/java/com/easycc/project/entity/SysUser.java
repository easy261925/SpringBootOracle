package com.easycc.project.entity;

import com.easycc.project.entity.Base;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2021-01-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("SYS_USER")
@ApiModel(value="SysUser对象", description="")
public class SysUser extends Base {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "机构id")
    @TableField("ORGANIZATION_ID")
    private Long organizationId;

    @ApiModelProperty(value = "登录名")
    @TableField("LOGIN_NAME")
    private String loginName;

    @ApiModelProperty(value = "登录密码")
    @TableField("PASSWORD")
    private String password;

    @TableField("USER_TYPE")
    private Long userType;

    @ApiModelProperty(value = "真实姓名")
    @TableField("USERNAME")
    private String username;

    @ApiModelProperty(value = "昵称")
    @TableField("NICKNAME")
    private String nickname;

    @ApiModelProperty(value = "性别")
    @TableField("GENDER")
    private String gender;

    @ApiModelProperty(value = "手机")
    @TableField("PHONE")
    private String phone;

    @ApiModelProperty(value = "座机")
    @TableField("TAX")
    private String tax;

    @ApiModelProperty(value = "邮箱")
    @TableField("MAIL")
    private String mail;

    @ApiModelProperty(value = "登录ip")
    @TableField("LOGIN_IP")
    private String loginIp;

    @ApiModelProperty(value = "最后登录时间")
    @TableField("LOGIN_TIME")
    private Date loginTime;

    @ApiModelProperty(value = "mac地址")
    @TableField("LOGIN_MAC")
    private String loginMac;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "身份证号")
    @TableField("ID_CARD")
    private String idCard;

    @ApiModelProperty(value = "职级")
    @TableField("JOB_CLASS")
    private String jobClass;

    @ApiModelProperty(value = "岗位状态")
    @TableField("POST_STATUS")
    private String postStatus;

    @ApiModelProperty(value = "岗位编码")
    @TableField("POST_CODE")
    private String postCode;

    @ApiModelProperty(value = "岗位描述")
    @TableField("POST_DESC")
    private String postDesc;

    @ApiModelProperty(value = "岗位类别")
    @TableField("POST_CATEGORY")
    private String postCategory;

    @ApiModelProperty(value = "职位编码")
    @TableField("JOB_CODE")
    private String jobCode;

    @ApiModelProperty(value = "职位名称")
    @TableField("JOB_NAME")
    private String jobName;

    @ApiModelProperty(value = "学历")
    @TableField("DEGREE")
    private String degree;

    @ApiModelProperty(value = "政治面貌")
    @TableField("POLITICS_STATUS")
    private String politicsStatus;

    @ApiModelProperty(value = "微信号")
    @TableField("WECHAT_ID")
    private String wechatId;

    @ApiModelProperty(value = "微信昵称")
    @TableField("WECHAT_NAME")
    private String wechatName;

    @ApiModelProperty(value = "照片")
    @TableField("AVATAR")
    private String avatar;

    @ApiModelProperty(value = "参加工作时间")
    @TableField("WORK_TIME")
    private Date workTime;

    @ApiModelProperty(value = "即时通号")
    @TableField("AM_CODE")
    private String amCode;

    @ApiModelProperty(value = "用户角色")
    @TableField("ROLE_TYPE")
    private String roleType;

    @ApiModelProperty(value = "微信 openId")
    @TableField("WECHAT_OPENID")
    private String wechatOpenid;


}
