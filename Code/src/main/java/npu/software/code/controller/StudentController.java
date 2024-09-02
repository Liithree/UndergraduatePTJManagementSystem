package npu.software.code.controller;

import lombok.extern.slf4j.Slf4j;
import npu.software.code.StaticValue;
import npu.software.code.pojo.Result;
import npu.software.code.pojo.Student;
import npu.software.code.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 管理员查看所有学生信息
     * API接口文档1.1
     */
    @GetMapping("/root/students")
    public Result list(@RequestHeader(name = "Authorization") String token){
        String uid = StaticValue.getUid(token);
        if(!StaticValue.rootAccount.equals(uid)){
            log.info("非管理员用户访问所有学生信息被拒绝");
            return Result.error("无权访问");
        }

        log.info("管理员查看所有学生用户信息");
        // 调用service查询学生信息
        List<Student> students = studentService.list();
        // 将密码置空
        students.forEach(student -> student.setPassword(null));
        return Result.success(students);
    }

    /**
     * 学生用户查看自己的信息
     * API接口文档：3.1
     */
    @GetMapping("/student/info")
    public Result getByUid(@RequestHeader(name = "Authorization") String token){
        String uid = StaticValue.getUid(token);

        Student student = studentService.getByUid(uid);
        student.setPassword(null); // 防止密码被传递
        return Result.success(student);
    }

    /**
     * 学生自行修改个人信息
     */
    @PutMapping("/student/info")
    public Result update(@RequestBody Student student){
        log.info("学生修改个人信息：{}", student);

        studentService.update(student);
        return Result.success();
    }

    /**
     * 学生修改密码
     * API接口文档：3.2
     */
    @PatchMapping("/student/pwd")
    public Result updatePwd(@RequestBody Map<String, String> params, @RequestHeader(name = "Authorization") String token){
        // 1.校验参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if(!StringUtils.hasText(oldPwd) || !StringUtils.hasText(newPwd) || !StringUtils.hasText(rePwd)){
            return Result.error("缺少必要参数");
        }

        // 校验原密码是否正确
        String uid = StaticValue.getUid(token);
        Student loginStudent = studentService.getByUid(uid);
        if(!loginStudent.getPassword().equals(oldPwd)){
            return Result.error("原密码错误");
        }

        // 校验新密码是否一致
        if(!rePwd.equals(newPwd)){
            return Result.error("两次输入密码不一致");
        }

        // 2.使用service更新密码
        studentService.updatePwd(newPwd, uid);
        log.info("学生修改密码成功：{}", uid);
        return Result.success();
    }
}
