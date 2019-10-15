package com.utils.work.jxkp.controller;

import com.utils.work.http.GlobalMessage;
import com.utils.work.jxkp.DTO.CommitDTO;
import com.utils.work.jxkp.DTO.CommitStaffDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/commit")
public class CommitController {
    @ApiOperation(value = "主管身份评价人列表")
    @GetMapping(value = "/first")
    // todo 传入的秦皇岛邮箱用authCode？ 有关id的传法
    public List<CommitStaffDTO> getFirstList(@RequestParam String mailQhd) {
        CommitStaffDTO commitStaffDTO = new CommitStaffDTO();
        List<CommitStaffDTO> commitStaffDTOS = new ArrayList<>();
        commitStaffDTOS.add(commitStaffDTO);
        return commitStaffDTOS;
    }
    @ApiOperation(value = "项目经理身份评价人列表")
    @GetMapping(value = "/pm")
    // todo 传入的秦皇岛邮箱用authCode？ 有关id的传法
    public List<CommitStaffDTO> getPMList(@RequestParam String mailQhd) {
        CommitStaffDTO commitStaffDTO = new CommitStaffDTO();
        List<CommitStaffDTO> commitStaffDTOS = new ArrayList<>();
        commitStaffDTOS.add(commitStaffDTO);
        return commitStaffDTOS;
    }

    @ApiOperation(value = "获得某个人的待评价信息")
    @GetMapping(value = "/staff")
    // todo 传入的秦皇岛邮箱用authCode？ 有关id的传法
    public CommitDTO getStaff(@RequestParam String mailQhd) {
        return new CommitDTO();
    }

    @ApiOperation(value = "保存某个人的待评价信息")
    @PostMapping(value = "/staff")
    // todo 传入的秦皇岛邮箱用authCode？ 有关id的传法
    public GlobalMessage saveStaff(@RequestBody CommitDTO commitDTO) {
        return new GlobalMessage();
    }

    @ApiOperation(value = "提交某个人的待评价信息")
    @PutMapping(value = "/staff")
    // todo 传入的秦皇岛邮箱用authCode？ 有关id的传法
    public GlobalMessage submitStaff(@RequestBody CommitDTO commitDTO) {
        return new GlobalMessage();
    }
}
