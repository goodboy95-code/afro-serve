package ink.afro.security.service;

import ink.afro.async.AsyncFactory;
import ink.afro.constant.Constants;
import ink.afro.entity.SysUser;
import ink.afro.entity.other.LoginCondition;
import ink.afro.entity.other.LoginUser;
import ink.afro.exception.type.ServiceException;
import ink.afro.service.SysUserService;
import ink.afro.utils.IPUtils;
import ink.afro.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 登录服务
 *
 * @author goodboy95-code
 */
@Service("loginService")
public class LoginService {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    private final SysUserService sysUserService;

    private final RedisUtils<String> redisUtils;

    private final ScheduledExecutorService scheduledExecutorService;

    @Autowired
    public LoginService(AuthenticationManager authenticationManager, TokenService tokenService, SysUserService sysUserService, RedisUtils<String> redisUtils, ScheduledExecutorService scheduledExecutorService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.sysUserService = sysUserService;
        this.redisUtils = redisUtils;
        this.scheduledExecutorService = scheduledExecutorService;
    }

    public String login(LoginCondition loginCondition) {
        validateCaptcha(loginCondition.getUsername(), loginCondition.getCaptchaImageCode(), loginCondition.getUuid());

        Authentication authentication;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginCondition.getUsername(), loginCondition.getPassword());
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(authenticationToken);
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                TimerTask timerTask = AsyncFactory.recordLoginLog(loginCondition.getUsername(), Constants.LOGIN_FAIL, "账号密码不匹配");
                scheduledExecutorService.schedule(timerTask, 10, TimeUnit.SECONDS);
                throw new ServiceException("账号密码不匹配");
            } else {
                TimerTask timerTask = AsyncFactory.recordLoginLog(loginCondition.getUsername(), Constants.LOGIN_FAIL, e.getMessage());
                scheduledExecutorService.schedule(timerTask, 10, TimeUnit.SECONDS);
                throw new ServiceException(e.getMessage());
            }
        }
        TimerTask timerTask = AsyncFactory.recordLoginLog(loginCondition.getUsername(), Constants.LOGIN_SUCCESS, "登录成功");
        scheduledExecutorService.schedule(timerTask, 10, TimeUnit.SECONDS);
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        recordLoginInfo(loginUser.getUser().getUserId());
        return tokenService.createToken(loginUser);
    }

    public void validateCaptcha(String username, String code, String uuid) {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + Optional.ofNullable(uuid).orElse("");
        String captcha = redisUtils.getCacheObject(verifyKey);
        redisUtils.deleteCacheObject(verifyKey);
        if (captcha == null) {
            TimerTask timerTask = AsyncFactory.recordLoginLog(username, Constants.LOGIN_FAIL, "验证码不存在或已过期");
            scheduledExecutorService.schedule(timerTask, 10, TimeUnit.SECONDS);
            throw new ServiceException("验证码不存在或已过期");
        }
        if (!code.equalsIgnoreCase(captcha)) {
            TimerTask timerTask = AsyncFactory.recordLoginLog(username, Constants.LOGIN_FAIL, "验证码不匹配");
            scheduledExecutorService.schedule(timerTask, 10, TimeUnit.SECONDS);
            throw new ServiceException("验证码不匹配");
        }
    }

    public void recordLoginInfo(Long userId) {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setLoginIp(IPUtils.getIpAddr());
        sysUser.setLoginDate(LocalDateTime.now());
        sysUserService.update(sysUser);
    }
}
