package npu.software.code.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import npu.software.code.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 令牌验证
        String token = request.getHeader("Authorization");

        // 验证token
        try{
            Map<String, Object> claims = JwtUtils.parseJwt(token);
            // 放行
            return true;
        }catch (Exception e){
            // http相应状态码为401
            response.setStatus(401);
            // 不放行
            return false;
        }
    }

}
