package com.utils.work.jxkp.DTO;

import lombok.Data;

@Data
public class PlanReq {
    private String chinessName;
    private int year;
    private String startTime;
    private String endTime;
    private String kpiUserId;
}
