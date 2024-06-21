package ink.afro.constant;


/**
 * 通用常量信息
 *
 * @author goodboy95-code
 */
public class Constants {
    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_code:";

    /**
     * 登录用户编号 redis key
     */
    public static final String LOGIN_USERID_KEY = "login_userId:";

    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";
    /**
     * 短信验证码 redis key
     */
    public static final String SMS_CODE = "SMS:";
    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * token中UUID的key
     */
    public static final String LOGIN_USER_KEY = "login_user_key";

    /**
     * 数据data
     */
    public static final String DATA = "data";

    /**
     * 令牌
     */
    public static final String TOKEN = "token";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/resourceMapping";
    /**
     * 限流 redis key
     */
    public static final String RATE_LIMIT_KEY = "rate_limit:";
    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";
    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";
    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 注册
     */
    public static final String REGISTER = "Register";
    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

}
