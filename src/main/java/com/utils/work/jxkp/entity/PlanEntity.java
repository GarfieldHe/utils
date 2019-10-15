package com.utils.work.jxkp.entity;

import lombok.Data;

@Data
public class PlanEntity {
    private String id;
    private String chinessName;
    private int year;
    private String startTime;
    private String endTime;
    private String kpiUserId;
    private int planState;
}
