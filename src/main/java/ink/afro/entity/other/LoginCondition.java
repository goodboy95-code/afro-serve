package ink.afro.entity.other;

import lombok.Data;

/**
 * 用户信息（参数）
 *
 * @author goodboy95-code
 */
@Data
public class LoginCondition {
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 验证码
     */
    private String captchaImageCode;
    /**
     * uuid
     */
    private String uuid;
}
