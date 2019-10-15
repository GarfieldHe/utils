package com.utils.work.jxkp.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ContributionEntity {
    private String id;
    @ApiModelProperty("人员信息表id")
    private String staffId;
    @ApiModelProperty("院内药品代码")private String contriType;
    @ApiModelProperty("院内药品代码")private int contriNum;
    @ApiModelProperty("院内药品代码")private String contriSuper;
    @ApiModelProperty("院内药品代码")private BigDecimal contriScore;

}
