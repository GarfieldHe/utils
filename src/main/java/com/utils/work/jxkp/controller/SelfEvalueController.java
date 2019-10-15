package com.utils.work.jxkp.controller;

import com.utils.work.http.GlobalMessage;
import com.utils.work.jxkp.DTO.SelfJXReq;
import com.utils.work.jxkp.DTO.SelfResultDTO;
import com.utils.work.jxkp.DTO.WorkReq;
import com.utils.work.jxkp.entity.KpiEntity;
import com.utils.work.jxkp.entity.WorkEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/jxkp/self")
public class SelfEvalueController {
    @ApiOperation("保存评估周期")
    @PostMapping(value="period")
    // todo 传入的秦皇岛邮箱用authCode？ 有关id的传法
    public GlobalMessage savePeriod(@RequestParam int period, String mailQhd) {
        return new GlobalMessage();
    }

    @ApiOperation("保存绩效工作计划")
    @PostMapping(value="work")
    public GlobalMessage savePeriod(@RequestBody WorkReq workReq) {
        return new GlobalMessage();
    }

    @ApiOperation("保存核心行为评估")
    @PostMapping(value="kpi")
    public GlobalMessage saveKpi(@RequestBody List<KpiEntity> kpiEntities) {
        return new GlobalMessage();
    }

    @ApiOperation("提交个人绩效评价")
    @PutMapping(value = "jxkp")
    // todo 传入的秦皇岛邮箱用authCode？ 有关id的传法
    public GlobalMessage submitJXKP(@RequestBody SelfJXReq selfJXReq) {
        return new GlobalMessage();
    }

    @ApiOperation("绩效评价结果汇总")
    @PostMapping(value="result")
    // todo 传入的秦皇岛邮箱用authCode？ 有关id的传法
    // 调用此接口时，所有的主管评价都不会有
    public SelfResultDTO getSelfResult(@RequestParam String mailQhd) {
        return new SelfResultDTO();
    }

    @ApiOperation(value = "绩效考评结果导出文件")
    @GetMapping(value = "/pdf")
    public ResponseEntity<byte[]> getResult(@RequestParam String planId,
                                            @RequestParam(required = false) String kpiState,
                                            @RequestParam(required = false) String dep) {
        RestTemplate restTemplate = new RestTemplate();
        byte[] bytes = restTemplate.getForObject("url", byte[].class);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "filename");
        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
    }
}
