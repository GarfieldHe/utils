package com.utils.work.jxkp.DTO;

import com.utils.work.jxkp.entity.StaffEntity;
import lombok.Data;

import java.util.List;

@Data
public class StaffRes {
    private List<StaffEntity> staffList;
    private List<StateDTO> stateList;
    private List<LevelRes> aList;
    private List<LevelRes> bList;
    private List<LevelRes> cList;
    private List<LevelRes> dList;
}
