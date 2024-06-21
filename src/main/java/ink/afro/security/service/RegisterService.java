package ink.afro.security.service;

import ink.afro.constant.Constants;
import ink.afro.entity.SysUser;
import ink.afro.entity.other.RegisterCondition;
import ink.afro.exception.type.ServiceException;
import ink.afro.service.SysUserService;
import ink.afro.utils.RedisUtils;
import ink.afro.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * 注册校验
 *
 * @author goodboy95-code
 */
@Component
public class RegisterService {
    private final SysUserService sysUserService;

    private final RedisUtils<String> redisUtils;

    @Autowired
    public RegisterService(SysUserService sysUserService, RedisUtils<String> redisUtils) {
        this.sysUserService = sysUserService;
        this.redisUtils = redisUtils;
    }

    /**
     * 注册
     */
    public String register(RegisterCondition registerCondition) {
        String msg, username = registerCondition.getUsername(), password = registerCondition.getPassword();
        SysUser sysUser = new SysUser();
        sysUser.setUserName(username);

        validateSmsCode(registerCondition.getSmsCode(), registerCondition.getUuid());

        if (!StringUtils.hasText(username)) {
            msg = "用户名不能为空";
        } else if (!StringUtils.hasText(password)) {
            msg = "用户密码不能为空";
        } else if (username.length() < 2
                || username.length() > 20) {
            msg = "账户长度必须在2到20个字符之间";
        } else if (password.length() < 5
                || password.length() > 20) {
            msg = "密码长度必须在5到20个字符之间";
        } else if (!sysUserService.isUniqueUsername(sysUser)) {
            msg = "保存用户" + username + "失败，注册账号已存在";
        } else {
            sysUser.setNickName(username);
            sysUser.setPassword(SecurityUtils.encryptPassword(password));
            sysUser.setCreateBy("user");
            sysUser.setCreateTime(LocalDateTime.now());
            boolean regFlag = sysUserService.insert(sysUser);
            if (!regFlag) {
                throw new ServiceException("注册失败,请联系系统管理人员");
            } else {
                msg = "注册账号成功";
            }
        }
        return msg;
    }

    /**
     * 校验验证码
     *
     * @param smsCodeFromUser 用户传入的验证码
     * @param uuid            手机验证码对应的uuid
     */
    public void validateSmsCode(String smsCodeFromUser, String uuid) {
        String verifyKey = Constants.SMS_CODE + uuid;
        String smsCodeFromRedis = redisUtils.getCacheObject(verifyKey);
        //redisUtils.deleteObject(verifyKey);
        if (smsCodeFromRedis == null) {
            throw new ServiceException("验证码不存在或已过期");
        }
        if (!smsCodeFromRedis.equalsIgnoreCase(smsCodeFromUser)) {
            throw new ServiceException("验证码不匹配");
        }
    }
}
