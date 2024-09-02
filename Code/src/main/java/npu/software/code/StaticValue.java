package npu.software.code;

import npu.software.code.utils.JwtUtils;

import java.time.format.DateTimeFormatter;

public class StaticValue {
    /**
     * 管理员账号
     */
    public static String rootAccount = "root";

    /**
     * 管理员密码
     */
    public static String rootPassword = "1234";

    /**
     * 时间格式
     */
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    /**
     * 通过jwt令牌获取用户id
     * @param token
     * @return
     */
    public static String getUid(String token){
        return (String) JwtUtils.parseJwt(token).get("uid");
    }
}
