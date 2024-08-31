package npu.software.code.controller;

import lombok.extern.slf4j.Slf4j;
import npu.software.code.pojo.Result;
import npu.software.code.pojo.Student;
import npu.software.code.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import npu.software.code.utils.JwtUtils;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/login")
    public Result login(@RequestBody Student student){
        log.info("登录：{}", student);
        Student s = studentService.login(student);
        // 登录成功，生成令牌
        if(s != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("uid", s.getUid());
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }
        // 登录失败，返回错误信息
        return Result.error("用户名或密码错误");
    }
}
