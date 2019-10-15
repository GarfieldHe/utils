package com.utils.work.jxkp.DTO;

import lombok.Data;

@Data
// 计划中不不同状态的人数统计
public class StateDTO {
    private String state;
    private String num;
}
