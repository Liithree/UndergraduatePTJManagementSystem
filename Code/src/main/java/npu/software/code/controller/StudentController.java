package npu.software.code.controller;

import ch.qos.logback.core.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import npu.software.code.pojo.Result;
import npu.software.code.pojo.Student;
import npu.software.code.service.StudentService;
import npu.software.code.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 查看所有学生信息
     * @return
     */
    @GetMapping("/students")
    public Result list(){
        log.info("查看所有学生用户信息");

        // 调用service查询学生信息
        List<Student> students = studentService.list();
        return Result.success(students);
    }

    /**
     * 根据学号查询用户所有信息
     * @return
     */
    @GetMapping("/student/info")
    public Result getByUid(@RequestHeader(name = "Authorization") String token){
        Map<String, Object> map = JwtUtils.parseJwt(token);
        String uid = (String) map.get("uid");

        Student student = studentService.getByUid(uid);
        student.setPassword(null); // 防止密码被传递
        return Result.success(student);
    }

    /**
     * 学生自行修改个人信息
     * @return
     */
    @PutMapping("/student/info")
    public Result update(@RequestBody Student student){
        log.info("学生修改个人信息：{}", student);

        studentService.update(student);
        return Result.success();
    }

    /**
     * 学生修改密码
     * @param params
     * @param token
     * @return
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
        Map<String, Object> map = JwtUtils.parseJwt(token);
        String uid = (String) map.get("uid");
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
        return Result.success();
    }

}
