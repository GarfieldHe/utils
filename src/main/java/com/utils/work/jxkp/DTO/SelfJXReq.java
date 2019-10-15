package com.utils.work.jxkp.DTO;

import com.utils.work.jxkp.entity.KpiEntity;
import lombok.Data;

import java.util.List;

@Data
public class SelfJXReq {
    private int period;
    private String mailQhd;
    private WorkReq workReq;
    private List<KpiEntity> kpiEntities;
}
