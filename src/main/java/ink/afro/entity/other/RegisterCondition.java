package ink.afro.entity.other;

import lombok.Data;

/**
 * 用户注册对象
 *
 * @author goodboy95-code
 */
@Data
public class RegisterCondition {
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 手机号码
     */
    private String phoneNumber;
    /**
     * 短信验证码
     */
    private String smsCode;
    /**
     * 短信验证码redis key
     */
    private String uuid;
}
