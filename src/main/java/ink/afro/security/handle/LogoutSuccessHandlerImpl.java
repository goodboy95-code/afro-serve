package ink.afro.security.handle;

import ink.afro.utils.ServletUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * 自定义退出处理类 返回成功
 *
 * @author goodboy95-code
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    /**
     *
     * 退出成功处理
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        ServletUtils.renderObject(response, ResponseEntity.status(HttpStatus.OK).body("退出成功"));
    }
}
