package npu.software.code.controller;

import lombok.extern.slf4j.Slf4j;
import npu.software.code.pojo.Result;
import npu.software.code.pojo.WorkBill;
import npu.software.code.service.WorkBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class WorkBillController {
    @Autowired
    private WorkBillService workBillService;

    /**
     * 创建工单
     * API接口文档：3.4
     */
    @PostMapping("/workbill")
    public Result add(@RequestBody WorkBill workBill, @RequestHeader(name = "Authorization") String token){
        log.info("创建了一个工单");

        WorkBill w = workBillService.add(workBill, token);
        if(w == null){
            return Result.error("岗位已被申请");
        }
        return Result.success(w);
    }

    /**
     * 管理员查看所有工单
     * API接口文档1.2
     */
    @GetMapping("/root/workbills")
    public Result listRoot(){
        log.info("管理员查看所有工单");

        List<WorkBill> workBills = workBillService.list();
        return Result.success(workBills);
    }

    /**
     * 用户查看自己的工单
     * API接口文档：3.5
     */
    @GetMapping("/student/workbills")
    public Result listStudent(@RequestHeader(name = "Authorization") String token){
        log.info("用户查看自己的工单");

        List<WorkBill> workBills = workBillService.list(token);
        return Result.success(workBills);
    }

    /**
     * 变更岗位状态
     */
    @PutMapping("/student/workbills")
    public Result update(@RequestBody WorkBill workBill){
        // 更新工单状态
        log.info("变更岗位状态：{}", workBill);

        workBillService.update(workBill);
        return Result.success();
    }
}
