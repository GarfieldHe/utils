package com.utils.work.jxkp.controller;

import com.utils.work.jxkp.entity.AbilityEntity;
import com.utils.work.jxkp.entity.TargetEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jxkp/code")
public class CodeController {
    @ApiOperation(value = "绩效目标达成代码项")
    @GetMapping(value = "/target")
    public TargetEntity getTarget() {
        return new TargetEntity();
    }

    @ApiOperation(value = "KPI能力代码项")
    @GetMapping(value = "/ability")
    public AbilityEntity getAbility() {
        return new AbilityEntity();
    }
}
