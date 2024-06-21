package ink.afro.entity.other;

import ink.afro.entity.SysUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.Serial;
import java.util.*;

/**
 * 用户登录信息
 *
 * @author goodboy95-code
 */
@Data
public class LoginUser implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 用户登录成功token的Id
     */
    private String tokenId;
    /**
     * 登录时间
     */
    private Long loginTime;
    /**
     * 过期时间
     */
    private Long expireTime;
    /**
     * 登录IP地址
     */
    private String ipAddress;
    /**
     * 登录地点
     */
    private String loginLocation;
    /**
     * 浏览器类型
     */
    private String browserType;
    /**
     * 操作系统类型
     */
    private String operatingSystem;
    /**
     * 权限集合
     */
    private List<String> permissions;
    /**
     * 权限集合
     */
    private Map<Integer, List<String>> permissionsMap;
    /**
     * 用户信息
     */
    private SysUser user;

    public LoginUser() {
    }

    public LoginUser(SysUser user) {
        this.user = user;
    }

    public LoginUser(SysUser user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    public LoginUser(SysUser user, Map<Integer, List<String>> permissionsMap, Integer selectedRoleId) {
        this.user = user;
        this.permissionsMap = permissionsMap;
        this.permissions = permissionsMap.get(selectedRoleId);
    }

    /**
     * 指示用户账户是否已过期。过期的账户无法进行身份验证。
     * 返回值:true 账户有效（即未过期）， false 不再有效（即已过期）
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     * true 用户未锁定， false 用户锁定
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示用户的凭据（密码）是否已过期。过期的凭据会阻止身份验证。
     * 返回值:true 用户的凭据有效（即未过期），false 不再有效（即已过期）
     */
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 指示该用户是启用还是禁用。无法对已禁用的用户进行身份验证。
     * 返回值:true 用户已启用，false 用户已禁用
     */
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * 返回授予用户的权限
     */
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        // 将权限信息封装成 GrantedAuthority 对象
        if (!ObjectUtils.isEmpty(user) && !CollectionUtils.isEmpty(permissions)) {
            for (String permission : permissions) {
                if (StringUtils.hasText(permission)) {
                    authorities.add(new SimpleGrantedAuthority(permission));
                }
            }
        }
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return user.getUserName();
    }
}
