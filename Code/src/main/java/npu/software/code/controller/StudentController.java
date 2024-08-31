package npu.software.code.controller;

import lombok.extern.slf4j.Slf4j;
import npu.software.code.pojo.Result;
import npu.software.code.pojo.Student;
import npu.software.code.service.StudentService;
import npu.software.code.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

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
        return Result.success(student);
    }

}
