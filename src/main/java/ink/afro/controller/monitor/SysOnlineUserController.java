package ink.afro.controller.monitor;

import ink.afro.constant.Constants;
import ink.afro.entity.SysOnlineUser;
import ink.afro.entity.other.LoginUser;
import ink.afro.service.SysOnlineUserService;
import ink.afro.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 在线用户监控
 *
 * @author goodboy95-code
 */
@RestController
@RequestMapping("/monitor")
public class SysOnlineUserController {
    private final SysOnlineUserService sysOnlineUserService;

    private final RedisUtils<LoginUser> redisUtils;

    @Autowired
    public SysOnlineUserController(SysOnlineUserService sysOnlineUserService, RedisUtils<LoginUser> redisUtils) {
        this.sysOnlineUserService = sysOnlineUserService;
        this.redisUtils = redisUtils;
    }

    @GetMapping("/queryByCondition")
    public ResponseEntity<List<SysOnlineUser>> queryByCondition(String ipaddr, String userName) {
        Set<String> keys = redisUtils.keys(Constants.LOGIN_TOKEN_KEY + "*");
        List<SysOnlineUser> userOnlineList = new ArrayList<>();
        for (String key : keys) {
            LoginUser user = redisUtils.getCacheObject(key);
            if (!ObjectUtils.isEmpty(ipaddr) && !ObjectUtils.isEmpty(userName)) {
                userOnlineList.add(sysOnlineUserService.getOnlineUserByInfo(ipaddr, userName, user));
            } else if (!ObjectUtils.isEmpty(ipaddr)) {
                userOnlineList.add(sysOnlineUserService.getOnlineUserByIpaddr(ipaddr, user));
            } else if (!ObjectUtils.isEmpty(userName) && !ObjectUtils.isEmpty(user.getUser())) {
                userOnlineList.add(sysOnlineUserService.getOnlineUserByUserName(userName, user));
            } else {
                userOnlineList.add(sysOnlineUserService.loginUserToOnlineUser(user));
            }
        }
        Collections.reverse(userOnlineList);
        userOnlineList.removeAll(Collections.singleton(null));
        return ResponseEntity.ok(userOnlineList);
    }

    /**
     * 强退用户
     */
    @DeleteMapping("/forceLogout")
    public ResponseEntity<String> forceLogout(String tokenId) {
        redisUtils.deleteCacheObject(Constants.LOGIN_TOKEN_KEY + tokenId);
        return ResponseEntity.ok("用户已退出");
    }
}
