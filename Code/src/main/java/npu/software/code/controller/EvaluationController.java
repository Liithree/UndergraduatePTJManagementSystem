package npu.software.code.controller;

import lombok.extern.slf4j.Slf4j;
import npu.software.code.pojo.Result;
import npu.software.code.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class EvaluationController {
    @Autowired
    private EvaluationService evaluationService;

    /**
     * 用户给岗位添加评价
     * @return
     */
    @PostMapping("")
    public Result add(){
        return Result.success();
    }
}
