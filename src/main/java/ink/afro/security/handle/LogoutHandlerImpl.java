package ink.afro.security.handle;

import ink.afro.entity.other.LoginUser;
import ink.afro.exception.type.ServiceException;
import ink.afro.security.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.util.ObjectUtils;

/**
 * 自定义退出处理类 返回成功
 *
 * @author goodboy95-code
 */
@Configuration
public class LogoutHandlerImpl implements LogoutHandler {
    private final TokenService tokenService;

    @Autowired
    public LogoutHandlerImpl(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    /**
     *  退出处理
     *
     * @param request the HTTP request
     * @param response the HTTP response
     * @param authentication the current principal details
     */
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (!ObjectUtils.isEmpty(loginUser)) {
            // 删除用户缓存记录
            boolean b = tokenService.delLoginUser(loginUser.getTokenId());
            if (!b) {
                // 退出成功，清除当前用户登录信息
                throw new ServiceException("删除用户缓存记录失败");
            }
        }
    }
}
