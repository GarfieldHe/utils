package com.utils.work.jxkp.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ComprehenEntity {
    private String id;
    @ApiModelProperty("人员信息表id")
    private String staffId;
    @ApiModelProperty("自评内容")
    private String selfContent;
    @ApiModelProperty("自评等级")
    private String selfLevel;
    @ApiModelProperty("自评时间")
    private String selfTime;
    @ApiModelProperty("一级主管评价内容")
    private String[] firstContent;
    @ApiModelProperty("一级主管评价等级")
    private String firstLevel;
    @ApiModelProperty("一级主管评价时间")
    private String firstTime;
    @ApiModelProperty("一级主管姓名")
    private String firstName;
    @ApiModelProperty("一级主管秦皇岛邮箱前缀")
    private String firstMail;
    @ApiModelProperty("二级主管评价内容")
    private String[] secodeContent;
    @ApiModelProperty("二级主管评价等级")
    private String secodeLevel;
    @ApiModelProperty("二级主管评价时间")
    private String secodeTime;
    @ApiModelProperty("二级主管姓名")
    private String secodeName;
    @ApiModelProperty("二级主管秦皇岛邮箱前缀")
    private String secodeMail;
}
