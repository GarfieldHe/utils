package com.utils.work.jxkp.entity;

import lombok.Data;

@Data
public class PerformRelationEntity {
    private String id;
    private String staffId;
    private String supervisorFirstId;
    private String supervisorSecondId;
    private String approverId;
    private String kpiPlanId;
}
