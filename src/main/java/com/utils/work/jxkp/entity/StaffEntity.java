package com.utils.work.jxkp.entity;

import lombok.Data;

@Data
public class StaffEntity {
    private String id;
    private String staffNum;
    private String staffName;
    private String dep;
    private String seconeDep;
    private String position;
    private String admissionTime;
    private String kpiPlanId;
    private int positonClass;
    private String mailQhd;
    private int period;
    private int kpiState;
}
