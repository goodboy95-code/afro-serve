package ink.afro.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 操作日志记录表(SysOperateLog)实体类
 *
 * @author goodboy95-code
 */
@Data
public class SysOperateLog {
    /**
     * 日志主键
     */
    private Long operateLogId;
    /**
     * 系统模块
     */
    private String title;
    /**
     * 操作类型（0其它 1新增 2修改 3删除）
     */
    private String operateType;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 请求方式
     */
    private String requestMethod;
    /**
     * 操作类别（0其它 1后台用户 2手机端用户）
     */
    private String operatorType;
    /**
     * 操作人员
     */
    private String operator;
    /**
     * 请求URL
     */
    private String requestUrl;
    /**
     * 主机地址
     */
    private String ipAddress;
    /**
     * 操作地点
     */
    private String operateLocation;
    /**
     * 请求参数
     */
    private String requestParam;
    /**
     * 返回参数
     */
    private String jsonResult;
    /**
     * 操作状态（0正常 1异常）
     */
    private String status;
    /**
     * 错误消息
     */
    private String errorMsg;
    /**
     * 操作时间
     */
    private LocalDateTime operateTime;
    /**
     * 查询的开始时间
     */
    private LocalDateTime beginOperateTime;
    /**
     * 查询的结束时间
     */
    private LocalDateTime endOperateTime;
    /**
     * 花费时间
     */
    private Long costTime;
}

