package ink.afro.entity;

import lombok.Data;

import java.time.LocalDateTime;


/**
 * 当前在线用户
 *
 * @author goodboy95-code
 * @since 2023-11-01 11:04:27
 */

@Data
public class SysOnlineUser {
    //令牌ID
    private String tokenId;
    //部门名称
    private String departmentName;
    //用户名称
    private String userName;
    //登录IP地址
    private String ipAddress;
    //登录地址
    private String loginLocation;
    //浏览器类型
    private String browserType;
    //操作系统
    private String operatingSystem;
    //登录时间
    private LocalDateTime loginTime;
}
