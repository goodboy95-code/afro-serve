package ink.afro.aop;

import ink.afro.annotation.Log;
import ink.afro.async.AsyncFactory;
import ink.afro.entity.SysOperateLog;
import ink.afro.entity.other.LoginUser;
import ink.afro.enums.BusinessStatus;
import ink.afro.utils.IPUtils;
import ink.afro.utils.LocationUtils;
import ink.afro.utils.SecurityUtils;
import ink.afro.utils.ServletUtils;
import com.alibaba.fastjson2.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 操作日志记录处理
 *
 * @author goodboy95-code
 */
@Aspect
@Component
public class LogAspect {
    private final ScheduledExecutorService scheduledExecutorService;

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 计算操作消耗时间
     */
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new NamedThreadLocal<>("Cost Time");

    @Autowired
    public LogAspect(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = scheduledExecutorService;
    }

    /**
     * 处理请求前执行
     */
    @Before(value = "@annotation(log)")
    public void boBefore(JoinPoint joinPoint, Log log) {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "@annotation(log)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Log log, Object jsonResult) {
        handleLog(joinPoint, log, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "@annotation(log)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Log log, Exception e) {
        handleLog(joinPoint, log, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, Log log, final Exception e, Object jsonResult) {
        try {
            SysOperateLog sysOperateLog = new SysOperateLog();
            // 设置标题
            sysOperateLog.setTitle(log.title());
            // 设置操作类型（0其它 1查询 2新增 3修改 4删除）
            sysOperateLog.setOperateType(String.valueOf(log.operateType().ordinal()));
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            sysOperateLog.setMethodName(className + "." + methodName + "()");
            // 设置请求方式
            sysOperateLog.setRequestMethod(ServletUtils.getRequest().getMethod());
            // 操作人员类别（0 其它 1 后台用户 2 手机端用户）
            sysOperateLog.setOperatorType(String.valueOf(log.operatorType().ordinal()));
            // 设置操作人
            LoginUser loginUser = SecurityUtils.getLoginUser();
            if (loginUser != null) {
                sysOperateLog.setOperator(loginUser.getUsername());
            }
            //设置请求URL
            String url = ServletUtils.getRequest().getRequestURI();
            sysOperateLog.setRequestUrl(url);
            //设置主机地址
            String clientIp = IPUtils.getIpAddr(ServletUtils.getRequest());
            sysOperateLog.setIpAddress(clientIp);
            sysOperateLog.setOperateLocation(LocationUtils.getLocation(clientIp).getCity());
            //设置请求参数
            Map<String, String[]> parameterMap = ServletUtils.getRequest().getParameterMap();
            sysOperateLog.setRequestParam(JSON.toJSONString(parameterMap));
            //设置操作状态（0正常 1异常）
            sysOperateLog.setStatus(String.valueOf(BusinessStatus.SUCCESS.ordinal()));
            //设置错误消息
            if (e != null) {
                sysOperateLog.setStatus(String.valueOf(BusinessStatus.FAIL.ordinal()));
                if (e.getMessage()!=null && e.getMessage().length()>20){
                    sysOperateLog.setErrorMsg(e.getMessage().substring(0,20));
                }else if (e.getMessage()!=null){
                    sysOperateLog.setErrorMsg(e.getMessage());
                }
            }
            //设置操作时间
            LocalDateTime localDateTime = Instant.ofEpochMilli(TIME_THREADLOCAL.get())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            sysOperateLog.setOperateTime(localDateTime);
            //设置消耗时间
            sysOperateLog.setCostTime(System.currentTimeMillis() - TIME_THREADLOCAL.get());
            scheduledExecutorService.schedule(AsyncFactory.recordOperateLog(sysOperateLog), 10, TimeUnit.MICROSECONDS);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            TIME_THREADLOCAL.remove();
        }
    }
}

