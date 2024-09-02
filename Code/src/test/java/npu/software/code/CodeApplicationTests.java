package npu.software.code;


import npu.software.code.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class HelloWorldApplicationTests {

    @Test
    void contextLoads() {
        Map<String, Object> map = JwtUtils.parseJwt("eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiJyb290IiwicGFzc3dvcmQiOiIxMjM0IiwiZXhwIjoxNzI1MjM3NTI4fQ.wTe17JmOdjc0fxMUBhOmYSAF3572FYRBw4I__qRhtrg");
        String uid = (String) JwtUtils.parseJwt("eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiJyb290IiwicGFzc3dvcmQiOiIxMjM0IiwiZXhwIjoxNzI1MjM3NTI4fQ.wTe17JmOdjc0fxMUBhOmYSAF3572FYRBw4I__qRhtrg").get("uid");
        System.out.println(map.toString());
    }

}
