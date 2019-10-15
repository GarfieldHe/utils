package com.utils.work.jxkp.controller;

import com.utils.work.http.GlobalMessage;
import com.utils.work.jxkp.Constants;
import com.utils.work.jxkp.DTO.PlanReq;
import com.utils.work.jxkp.DTO.StaffRes;
import com.utils.work.jxkp.entity.PerformRelationEntity;
import com.utils.work.jxkp.entity.PlanEntity;
import com.utils.work.jxkp.entity.StaffEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jxkp/hr")
public class HRController {
    @ApiOperation(value = "年份考核信息")
    @GetMapping(value = "/plan")
    public List<PlanEntity> getPlan() {
        PlanEntity planEntity = new PlanEntity();
        List<PlanEntity> planEntities = new ArrayList<>();
        planEntities.add(planEntity);
        return planEntities;
    }
    @ApiOperation(value="新建考核活动")
    @PutMapping(value="/plan")
    public PlanEntity addMongo(@RequestBody PlanReq planReqDTO) {
        PlanEntity planEntity = new PlanEntity();
        BeanUtils.copyProperties(planReqDTO, planEntity);
        planEntity.setPlanState(Constants.PLAN_STATE.PREPIRE);  // todo 加定时任务在启动时间修改考核进度
        // todo 正式项目文件中放开 引neuq工作包
//        planEntity.setId(UUIDUtils.uuid32());
        // todo 数据库存储
        return planEntity;
    }

    @ApiOperation(value = "人员基本信息、绩效考评结果 搜索")
    @GetMapping(value = "/staff")
    public StaffRes /*Page<StaffEntity>*/ getStaff(@RequestParam String planId,
                                                   @RequestParam String sign, // 性能优化，区别人员基本信息和绩效考评？如果人员基本信息则不需要返回其他list
                                                   @RequestParam(required = false) String staffName,
                                                   @RequestParam(required = false) String dep,
                                                   @RequestParam(required = false) String pageNo,
                                                   @RequestParam(required = false) String pageSize) {
        StaffRes staffRes = new StaffRes();
        StaffEntity staffEntity = new StaffEntity();
        List<StaffEntity> staffEntities = new ArrayList<>();
        staffEntities.add(staffEntity);
//        Pageable pageable = new PageRequest(pageNo, pageSize, Sort.Direction.DESC, "planId");
        staffRes.setStaffList(staffEntities);
        return staffRes;
    }

    @ApiOperation(value="人员基本信息导入")
    @PostMapping(value = "/staff")
    public GlobalMessage putStaff(@RequestParam MultipartFile mf, String planId) throws IOException {
        return new GlobalMessage("0","导入成功");
    }

    @ApiOperation(value = "绩效评价关系搜索")
    @GetMapping(value = "/relationship")
    public  List<PerformRelationEntity> /*Page<StaffEntity>*/ getRelation(@RequestParam String planId,
                                                                          @RequestParam(required = false) String staffName,
                                                                          @RequestParam(required = false) String year,
                                                                          @RequestParam(required = false) String pageNo,
                                                                          @RequestParam(required = false) String pageSize) {
        PerformRelationEntity performRelationEntity = new PerformRelationEntity();
        List<PerformRelationEntity> performRelationEntities = new ArrayList<>();
        performRelationEntities.add(performRelationEntity);
//        Pageable pageable = new PageRequest(pageNo, pageSize, Sort.Direction.DESC, "planId");
        return performRelationEntities;
    }

    @ApiOperation(value="绩效评价关系导入")
    @PostMapping(value = "/relationship")
    public GlobalMessage putRelationship(@RequestParam MultipartFile mf, String planId) throws IOException {
        return new GlobalMessage("0","导入成功");
    }
    @ApiOperation(value = "绩效考评结果导出文件")
    @GetMapping(value = "/result")
    public ResponseEntity<byte[]> getResult(@RequestParam String planId,
                                                                 @RequestParam(required = false) String kpiState,
                                                                 @RequestParam(required = false) String dep) {
        // todo 此为pdf打印，excel导出待处理
        RestTemplate restTemplate = new RestTemplate();
        byte[] bytes = restTemplate.getForObject("url", byte[].class);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "filename");
        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
    }
}
