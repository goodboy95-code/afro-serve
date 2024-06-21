package ink.afro.controller.common;

import ink.afro.constant.Constants;
import ink.afro.entity.SysRole;
import ink.afro.entity.other.LoginCondition;
import ink.afro.entity.other.LoginUser;
import ink.afro.entity.params.UserInfo;
import ink.afro.entity.responseData.AxiosData;
import ink.afro.exception.type.ServiceException;
import ink.afro.security.service.LoginService;
import ink.afro.security.service.TokenService;
import ink.afro.service.SysRoleService;
import ink.afro.service.SysUserService;
import ink.afro.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 登录验证
 *
 * @author goodboy95-code
 * @since 2023-09-14 10:15:19
 */
@RestController
public class LoginController {

    private final LoginService loginService;
    private final SysRoleService roleService;
    private final TokenService tokenService;

    private final SysUserService userService;

    @Autowired
    public LoginController(LoginService loginService, SysRoleService roleService, TokenService tokenService, SysUserService userService) {
        this.loginService = loginService;
        this.roleService = roleService;
        this.tokenService = tokenService;
        this.userService = userService;
    }


    /**
     * 登录
     *
     * @param loginCondition 登录信息
     * @return ResponseEntity 结果集
     */
    @PostMapping("/login")
    public ResponseEntity<AxiosData> login(@RequestBody LoginCondition loginCondition) {
        String token = "";
        if (!ObjectUtils.isEmpty(loginCondition)) {
            token = loginService.login(loginCondition);
        }
        return ResponseEntity.ok(AxiosData.setData(Constants.TOKEN, token));
    }

    /**
     * 获取用户信息
     *
     * @return AxiosResult 结果集
     */
    @GetMapping("/getInfo")
    public ResponseEntity<AxiosData> getInfo() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        List<String> permissions = loginUser.getPermissions();
        if (!CollectionUtils.isEmpty(permissions)) {
            permissions.remove("");
        }
        // 角色集合
        List<SysRole> roles = null;
        if (!ObjectUtils.isEmpty(loginUser.getUser().getUserId())) {
            roles = roleService.queryRoleByUserId(loginUser.getUser().getUserId());
        }
        AxiosData axiosData = AxiosData.setData();
        axiosData.put("user", loginUser.getUser());
        axiosData.put("roles", roles);
        axiosData.put("permissions", permissions);
        return ResponseEntity.ok(axiosData);
    }

    /**
     * 更新用户信息
     *
     * @return AxiosResult 结果集
     */
    @PutMapping("/editUserInfo")
    public ResponseEntity<String> editUserInfo(@RequestBody UserInfo userInfo) {
        boolean update = userService.update(userInfo.getUser());
        if (update) {
            LoginUser loginUser = SecurityUtils.getLoginUser();
            loginUser.setUser(userInfo.getUser());
            // 更新redis中的用户信息
            tokenService.setLoginUser(loginUser);
            return ResponseEntity.ok("更新成功");
        }else {
            throw new ServiceException("更新失败");
        }
    }
}
