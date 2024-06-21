package ink.afro.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 系统访问记录(SysLoginLog)实体类
 *
 * @author goodboy95-code
 */
@Data
public class SysLoginLog {
    /**
     * 访问ID
     */
    private Long loginLogId;
    /**
     * 用户账号
     */
    private String userName;
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
    private String browser;
    /**
     * 操作系统
     */
    private String os;
    /**
     * 登录状态（0成功 1失败）
     */
    private String status;
    /**
     * 提示消息
     */
    private String msg;
    /**
     * 访问时间
     */
    private LocalDateTime loginTime;

    /**
     * 查询的开始时间
     */
    private LocalDateTime beginLoginLogTime;
    /*
     * 查询的结束时间、
     */
    private LocalDateTime endLoginLogTime;
}

