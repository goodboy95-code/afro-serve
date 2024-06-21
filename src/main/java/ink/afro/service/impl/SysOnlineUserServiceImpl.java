package ink.afro.service.impl;


import ink.afro.entity.SysOnlineUser;
import ink.afro.entity.other.LoginUser;
import ink.afro.service.SysOnlineUserService;
import ink.afro.utils.ObjectUtils;
import ink.afro.utils.TimeUtils;
import org.springframework.stereotype.Service;

/**
 * 在线用户 服务实现类
 *
 * @author goodboy95-code
 */
@Service
public class SysOnlineUserServiceImpl implements SysOnlineUserService {
    @Override
    public SysOnlineUser getOnlineUserByIpaddr(String ipaddr, LoginUser user) {
        if (ObjectUtils.nullUnSafeEquals(ipaddr, user.getIpAddress())) {
            return loginUserToOnlineUser(user);
        }
        return null;
    }

    @Override
    public SysOnlineUser getOnlineUserByUserName(String userName, LoginUser user) {
        if (ObjectUtils.nullUnSafeEquals(userName, user.getUsername())) {
            return loginUserToOnlineUser(user);
        }
        return null;
    }

    @Override
    public SysOnlineUser getOnlineUserByInfo(String ipaddr, String userName, LoginUser user) {
        if (ObjectUtils.nullUnSafeEquals(ipaddr, user.getIpAddress()) && ObjectUtils.nullUnSafeEquals(userName, user.getUsername())) {
            return loginUserToOnlineUser(user);
        }
        return null;
    }

    @Override
    public SysOnlineUser loginUserToOnlineUser(LoginUser user) {
        if (ObjectUtils.isEmpty(user) || ObjectUtils.isEmpty(user.getUser())) {
            return null;
        }
        SysOnlineUser sysUserOnline = new SysOnlineUser();
        sysUserOnline.setTokenId(user.getTokenId());
        sysUserOnline.setUserName(user.getUsername());
        sysUserOnline.setIpAddress(user.getIpAddress());
        sysUserOnline.setLoginLocation(user.getLoginLocation());
        sysUserOnline.setBrowserType(user.getBrowserType());
        sysUserOnline.setOperatingSystem(user.getOperatingSystem());
        sysUserOnline.setLoginTime(TimeUtils.milliToLocalDateTime(user.getLoginTime()));
        return sysUserOnline;
    }
}
