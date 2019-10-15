package com.utils.work.jxkp.DTO;

import com.utils.work.jxkp.entity.*;
import lombok.Data;

import java.util.List;

@Data
// 主管评价dto
public class CommitDTO {
    private StaffEntity staffEntity;
    private List<WorkEntity> workEntities;
    private List<ContributionEntity> contributionEntities;
    private ComprehenEntity comprehenEntity;
    private List<KpiEntity> kpiEntities;

}
