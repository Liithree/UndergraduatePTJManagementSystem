package npu.software.code.controller;

import lombok.extern.slf4j.Slf4j;
import npu.software.code.StaticValue;
import npu.software.code.pojo.Position;
import npu.software.code.pojo.Result;
import npu.software.code.pojo.Student;
import npu.software.code.pojo.WorkBill;
import npu.software.code.service.PositionService;
import npu.software.code.service.StudentService;
import npu.software.code.service.WorkBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class WorkBillController {
    @Autowired
    private WorkBillService workBillService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private StudentService studentService;

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

    /**
     * 学生用户撤回工单
     * API接口文档：3.7
     */
    @PutMapping("/student/workbills/withdraw/{idWb}")
    public Result withdraw(@RequestHeader(name = "Authorization") String token, @PathVariable String idWb){
        String uid = StaticValue.getUid(token);
        WorkBill workBill = workBillService.getById(idWb);

        // 判断工单是否为用户自己的
        if(!workBill.getUid().equals(uid)){
            return Result.error("无权撤回");
        }

        // 判断工单状态是否为待审核或已通过
        if(workBill.getState() == 0 || workBill.getState() == 3){
            workBill.setState(2);
            workBill.setUpdateTime(LocalDateTime.now());
            workBillService.update(workBill);

            // 撤回后更新岗位状态
            Position p = positionService.getById(workBill.getIdPos());
            p.setState(1);
            p.setUpdateTime(LocalDateTime.now());
            positionService.update(p);
        }
        else{
            return Result.error("当前不可撤回");
        }
        return Result.success();
    }

    /**
     * 签到
     * API接口文档：2.4
     */
    @PutMapping("/signIn/{idWb}")
    public Result signIn(@PathVariable String idWb, @RequestHeader(name = "Authorization") String token){
        log.info("签到：{}", idWb);

        String uid = StaticValue.getUid(token);

        if(!uid.equals(StaticValue.signAccount)){
            return Result.error("无权签到");
        }
        WorkBill workBill = workBillService.getById(idWb);
        // 判断工单状态是否为已接受
        if(workBill.getState() != 3){
            return Result.error("当前不可签到");
        }
        workBill.setState(4);
        workBill.setSignInTime(LocalDateTime.now());
        workBill.setUpdateTime(LocalDateTime.now());
        workBillService.update(workBill);
        return Result.success();
    }

    /**
     * 签退
     * API接口文档：2.5
     */
    @PutMapping("/signOut/{idWb}")
    public Result signOut(@PathVariable String idWb, @RequestHeader(name = "Authorization") String token){
        log.info("签退：{}", idWb);

        String uid = StaticValue.getUid(token);

        if(!uid.equals(StaticValue.signAccount)){
            return Result.error("无权签退");
        }
        WorkBill workBill = workBillService.getById(idWb);
        // 判断工单状态是否为已签到
        if(workBill.getState() != 4){
            return Result.error("当前不可签退");
        }
        workBill.setState(5);
        workBill.setSignOutTime(LocalDateTime.now());
        workBill.setUpdateTime(LocalDateTime.now());
        workBillService.update(workBill);

        // 核算工时与工资
        Student student = studentService.getByUid(workBill.getUid());
        Position position = positionService.getById(workBill.getIdPos());
        student.setWorkTime(student.getWorkTime() + position.getWorkTime());
        student.setSalary(student.getSalary() + position.getSalary());
        studentService.update(student);
        return Result.success();
    }

    /**
     * 管理员同意或拒绝用户的岗位申请工单
     * API接口文档：1.5
     */
    @PutMapping("/root/workbills")
    public Result updateState(@RequestHeader(name = "Authorization") String token, @RequestBody WorkBill workBill){
        String uid = StaticValue.getUid(token);
        if(!StaticValue.rootAccount.equals(uid)){
            return Result.error("权限不足");
        }

        log.info("管理员同意或拒绝用户的岗位申请工单：{}", workBill);

        WorkBill w = workBillService.getById(workBill.getIdWb());
        if(w.getState() != 0){
            return Result.error("非法操作");
        }

        w.setState(workBill.getState());
        workBillService.update(w);
        return Result.success();
    }
}
