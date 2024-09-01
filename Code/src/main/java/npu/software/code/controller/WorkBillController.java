package npu.software.code.controller;

import lombok.extern.slf4j.Slf4j;
import npu.software.code.pojo.Result;
import npu.software.code.pojo.WorkBill;
import npu.software.code.service.WorkBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class WorkBillController {
    @Autowired
    private WorkBillService workBillService;

    /**
     * 创建工单
     * @return
     */
    @PostMapping("/workBill")
    public Result add(@RequestBody WorkBill workBill){
        log.info("创建了一个工单");

        workBillService.add(workBill);
        return Result.success();
    }
}
