package com.utils.work.jxkp.DTO;

import com.utils.work.jxkp.entity.ComprehenEntity;
import com.utils.work.jxkp.entity.ContributionEntity;
import com.utils.work.jxkp.entity.WorkEntity;
import lombok.Data;

import java.util.List;

@Data
public class WorkReq {
    private List<WorkEntity> workEntities;
    private ComprehenEntity comprehenEntity;
    private List<ContributionEntity> contributionEntities; // todo 贡献性评价表的代码项
}
