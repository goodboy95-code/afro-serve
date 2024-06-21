package ink.afro.security.handle;

import ink.afro.utils.ServletUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

/**
 * 认证失败处理类 返回没有认证
 *
 * @author goodboy95-code
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    @Serial
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        System.out.println(request.getRequestURI());
        if (e instanceof AuthenticationCredentialsNotFoundException) {
            int code = HttpStatus.NOT_FOUND.value();
            ServletUtils.renderObject(response, ResponseEntity.status(code).body("找不到认证对象（检查URL）"));
        } else {
            int code = HttpStatus.UNAUTHORIZED.value();
            ServletUtils.renderObject(response, ResponseEntity.status(code).body("认证失败，无法访问系统资源"));
        }
    }
}
