package npu.software.code.controller;

import lombok.extern.slf4j.Slf4j;
import npu.software.code.StaticValue;
import npu.software.code.pojo.Evaluation;
import npu.software.code.pojo.Position;
import npu.software.code.pojo.Result;
import npu.software.code.pojo.WorkBill;
import npu.software.code.service.EvaluationService;
import npu.software.code.service.PositionService;
import npu.software.code.service.WorkBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@RestController
public class EvaluationController {
    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private WorkBillService workBillService;

    @Autowired
    private PositionService positionService;

    /**
     * 用户给岗位添加评价
     * @return
     */
    @PostMapping("/student/workbills/evaluation")
    public Result add(@RequestHeader(name = "Authorization") String token,
                      @RequestBody Map<String, Object> params){
        // String idPos, Integer stars, String content, String idWb
        log.info("用户给岗位添加评价");

        String idPos = params.get("idPos").toString();
        Integer stars = (Integer) params.get("stars");
        String content = params.get("content").toString();
        String idWb = params.get("idWb").toString();

        String uid = StaticValue.getUid(token);
        WorkBill workBill = workBillService.getById(idWb);
        if(!workBill.getUid().equals(uid) || !workBill.getIdPos().equals(idPos)){
            return Result.error("工单信息不正确");
        }
        if(workBill.getState() != 5){
            return Result.error("工单状态不正确，不可评价");
        }

        evaluationService.add(uid, idPos, stars, content);

        // 设置工单状态为已评价
        workBill.setUpdateTime(LocalDateTime.now());
        workBill.setState(6);
        workBillService.update(workBill);
        return Result.success();
    }
}
