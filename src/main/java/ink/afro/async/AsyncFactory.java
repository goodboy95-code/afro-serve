package ink.afro.async;

import ink.afro.constant.Constants;
import ink.afro.entity.SysLoginLog;
import ink.afro.entity.SysOperateLog;
import ink.afro.entity.other.Location;
import ink.afro.service.SysLoginLogService;
import ink.afro.service.SysOperateLogService;
import ink.afro.utils.IPUtils;
import ink.afro.utils.LocationUtils;
import ink.afro.utils.LogUtils;
import ink.afro.utils.ServletUtils;
import ink.afro.utils.spring.SpringUtils;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.TimerTask;
import java.util.stream.Stream;

/**
 * 异步工厂（产生任务用）
 *
 * @author goodboy95-code
 */
public class AsyncFactory {
    private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");

    /**
     * 操作日志记录
     *
     * @param sysOperateLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOperateLog(final SysOperateLog sysOperateLog) {
        return new TimerTask() {
            @Override
            public void run() {
                SpringUtils.getBean(SysOperateLogService.class).insert(sysOperateLog);
            }
        };
    }

    public static TimerTask recordLoginLog(final String username, final String myStatus, final String message,
                                           final Object... args) {
        final UserAgent userAgent = UserAgentUtil.parse(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = IPUtils.getIpAddr();
        return new TimerTask() {
            @Override
            public void run() {
                Location location = LocationUtils.getLocation(ip);

                String s = LogUtils.getBlock(ip) +
                        location.getCity() +
                        LogUtils.getBlock(username) +
                        LogUtils.getBlock(myStatus) +
                        LogUtils.getBlock(message);
                // 打印信息到日志
                sys_user_logger.info(s, args);
                // 封装对象
                SysLoginLog sysLoginLog = new SysLoginLog();

                sysLoginLog.setUserName(username);
                sysLoginLog.setIpAddress(ip);
                sysLoginLog.setLoginLocation(location.getCity());
                sysLoginLog.setBrowser(userAgent.getBrowser().getName());
                sysLoginLog.setOs(userAgent.getOs().getName());
                sysLoginLog.setMsg(message);
                sysLoginLog.setLoginTime(LocalDateTime.now());
                // 日志状态
                boolean matched = Stream.of(Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER)
                        .anyMatch(status -> StringUtils.equals(myStatus, status));
                if (matched) {
                    sysLoginLog.setStatus(Constants.SUCCESS);
                } else if (Constants.LOGIN_FAIL.equals(myStatus)) {
                    sysLoginLog.setStatus(Constants.FAIL);
                }
                // 插入数据
                SpringUtils.getBean(SysLoginLogService.class).insert(sysLoginLog);
            }
        };
    }
}
