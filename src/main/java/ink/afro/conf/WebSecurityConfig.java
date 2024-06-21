package ink.afro.conf;

import ink.afro.entity.SysRole;
import ink.afro.entity.SysUser;
import ink.afro.entity.other.LoginUser;
import ink.afro.enums.UserStatus;
import ink.afro.exception.type.ServiceException;
import ink.afro.security.filter.JwtAuthenticationTokenFilter;
import ink.afro.security.handle.AuthenticationEntryPointImpl;
import ink.afro.security.handle.LogoutHandlerImpl;
import ink.afro.security.handle.LogoutSuccessHandlerImpl;
import ink.afro.service.SysMenuService;
import ink.afro.service.SysRoleService;
import ink.afro.service.SysUserService;
import ink.afro.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;

/*
  X-Frame-Options 有三个值:
  DENY
  表示该页面不允许在 frame 中展示，即便是在相同域名的页面中嵌套也不允许。
  SAME-ORIGIN
  表示该页面可以在相同域名页面的 frame 中展示。
  ALLOW-FROM uri
  表示该页面可以在指定来源的 frame 中展示。
 */

/**
 * spring security 配置类
 *
 * @author goodboy95-code
 **/
@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

    private final SysUserService sysUserService;

    private final SysRoleService sysRoleService;
    /**
     * token认证过滤器
     */
    private final JwtAuthenticationTokenFilter authenticationTokenFilter;

    private final AuthenticationEntryPointImpl authenticationEntryPoint;

    /**
     * 退出处理类
     */
    private final LogoutHandlerImpl logoutHandlerImpl;

    /**
     * 退出成功处理类
     */
    private final LogoutSuccessHandlerImpl logoutSuccessHandlerImpl;


    private final SysMenuService menuService;

    @Autowired
    public WebSecurityConfig(SysUserService sysUserService, SysRoleService sysRoleService, JwtAuthenticationTokenFilter authenticationTokenFilter, AuthenticationEntryPointImpl authenticationEntryPoint, LogoutHandlerImpl logoutHandlerImpl, LogoutSuccessHandlerImpl logoutSuccessHandlerImpl, SysMenuService menuService) {
        this.sysUserService = sysUserService;
        this.sysRoleService = sysRoleService;
        this.authenticationTokenFilter = authenticationTokenFilter;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.logoutHandlerImpl = logoutHandlerImpl;
        this.logoutSuccessHandlerImpl = logoutSuccessHandlerImpl;
        this.menuService = menuService;
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            SysUser sysUser = sysUserService.queryByName(username);
            if (ObjectUtils.isEmpty(sysUser)) {
                throw new ServiceException(MessageUtils.getMessage("user.not.exists"), 404);
            } else if (UserStatus.DELETED.getCode().equals(sysUser.getDelFlag())) {
                throw new ServiceException(MessageUtils.getMessage("user.password.delete"), 404);
            } else if (UserStatus.DISABLE.getCode().equals(sysUser.getStatus())) {
                throw new ServiceException(MessageUtils.getMessage("user.blocked"), 404);
            }
            List<SysRole> roles = null;
            Integer selectedRoleId = null;
            Map<Integer, List<String>> paramMap = new HashMap<>();
            if (!ObjectUtils.isEmpty(sysUser.getUserId())) {
                roles = sysRoleService.queryRoleByUserId(sysUser.getUserId());
            }
            // 使用Stream的forEach方法来遍历roles
            if (!CollectionUtils.isEmpty(roles)) {
                roles.forEach(role -> {
                    // 在这里，你可以对每个role进行操作
                    Integer roleId = role.getRoleId();
                    // 根据roleId查询菜单权限
                    List<String> perms = menuService.querySysMenuPermsByRoleId(roleId);
                    if (!CollectionUtils.isEmpty(perms)) {
                        perms.remove("");
                    }
                    paramMap.put(role.getRoleId(), perms);
                });
                Optional<Integer> roleId = roles.stream()
                        .min(Comparator.comparingInt(SysRole::getRoleSort))
                        .map(SysRole::getRoleId);
                if (roleId.isPresent()) {
                    selectedRoleId = roleId.get();
                }
            }
            return new LoginUser(sysUser, paramMap, selectedRoleId);
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        (authorizeHttpRequests) ->
                                authorizeHttpRequests
                                        .requestMatchers("/getCaptchaImage").permitAll()
                                        .requestMatchers("/login").permitAll()
                                        .requestMatchers("/register").permitAll()
                                        .requestMatchers("/sms/send").permitAll()
                                        .requestMatchers("/resourceMapping/**").permitAll()
                                        .requestMatchers("/publish").permitAll()
                                        .anyRequest().authenticated()
                );
        http
                .exceptionHandling(
                        (exceptionHandling) ->
                                exceptionHandling
                                        .authenticationEntryPoint(authenticationEntryPoint)
                );
        http
                .sessionManagement(
                        (sessionManagement) ->
                                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        http
                .addFilterBefore(authenticationTokenFilter, AuthorizationFilter.class);
        http
                .headers((headersConfigurer) ->
                        headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        http
                .logout(
                        (logout) ->
                                logout
                                        .logoutUrl("/logout")
                                        .addLogoutHandler(logoutHandlerImpl)
                                        .logoutSuccessHandler(logoutSuccessHandlerImpl)
                );
        http
                .anonymous(AbstractHttpConfigurer::disable);
        http
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
