package ink.afro.security.filter;

import ink.afro.entity.other.LoginUser;
import ink.afro.security.service.TokenService;
import ink.afro.utils.SecurityUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * token过滤器 验证token有效性；
 * getCredentials() 用户登录的凭证 一般指的就是密码；
 * getPrincipal() 这里认为Principal就为登录的用户；
 *
 * @author goodboy95-code
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private final TokenService tokenService;

    @Autowired
    public JwtAuthenticationTokenFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain)
            throws ServletException, IOException {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (!ObjectUtils.isEmpty(loginUser) && ObjectUtils.isEmpty(SecurityUtils.getAuthentication())) {
            String roleId = request.getHeader("RoleId");
            if (StringUtils.hasText(roleId)) {
                List<String> permissions = loginUser.getPermissionsMap().get(Integer.parseInt(roleId));
                loginUser.setPermissions(permissions);
            }
            tokenService.verifyRedisExpireTime(loginUser);
            //打上已经认证的标识 super.setAuthenticated(true);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            SecurityUtils.setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }
}
