package com.utils.work.jxkp.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WorkEntity {
    private String id;
    private String kpiStaffId;
    private String commitLevel;
    private BigDecimal score;
    private String proName;
    private String proApproveName;
    private String proApproveMail;
    private String proRole;
    private String proContent;
    private String proPeriod;
    private BigDecimal proWorkload;
    private String xsId;
    private int xsTarget;
    private String xsContent;
    private String xsSelfLevel;
    private BigDecimal weight;
}
