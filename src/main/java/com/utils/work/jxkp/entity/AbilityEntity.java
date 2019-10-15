package com.utils.work.jxkp.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AbilityEntity {

    private String id;
    @ApiModelProperty("能力项目")
    private String abilityType;
    @ApiModelProperty("能力定义")
    private String abilityDefine;
    @ApiModelProperty("典型行为要素描述")
    private String draw;
}
