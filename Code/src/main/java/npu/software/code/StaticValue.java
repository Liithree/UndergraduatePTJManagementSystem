package npu.software.code;

import npu.software.code.utils.JwtUtils;

import java.time.format.DateTimeFormatter;

public class StaticValue {
    /**
     * 管理员账号
     */
    public static final String rootAccount = "root";

    /**
     * 管理员密码
     */
    public static final String rootPassword = "1234";

    /**
     * 打卡机账号
     */
    public static final String signAccount = "sign";

    /**
     * 打卡机密码
     */
    public static final String signPassword = "2345";

    /**
     * 时间格式
     */
    public static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    /**
     * 通过jwt令牌获取用户id
     * @param token
     * @return
     */
    public static String getUid(String token){
        return (String) JwtUtils.parseJwt(token).get("uid");
    }
}
