package com.utils.work.jxkp.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class KpiEntity {
    private String id;
    private String abilityId;
    private String staffId;
    private String selfLevel;
    private String commitLevel;
    private BigDecimal score;
}
