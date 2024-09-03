package npu.software.code.controller;

import lombok.extern.slf4j.Slf4j;
import npu.software.code.StaticValue;
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

    /**
     * 用户登录
     * API接口文档：2.1
     */
    @PostMapping("/login")
    public Result login(@RequestBody Student student){
        log.info("登录：{}", student);
        String account = student.getUid();
        String password = student.getPassword();
        if(account.equals(StaticValue.rootAccount) && password.equals(StaticValue.rootPassword)){
            // 生成管理员令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("uid", StaticValue.rootAccount);
            claims.put("password", StaticValue.rootPassword);
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success("root" + ":" + jwt);
        }
        if(account.equals(StaticValue.signAccount) && password.equals(StaticValue.signPassword)){
            // 生成打卡机令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("uid", StaticValue.signAccount);
            claims.put("password", StaticValue.signPassword);
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success("sign" + ":" + jwt);
        }
        Student s = studentService.login(student);
        // 登录成功，生成令牌
        if(s != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("uid", s.getUid());
            claims.put("password", s.getName());
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }
        // 登录失败，返回错误信息
        return Result.error("用户名或密码错误");
    }
}
