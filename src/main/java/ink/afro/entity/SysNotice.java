package ink.afro.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 通知公告表(SysNotice)实体类
 *
 * @author goodboy95-code
 */
@Data
public class SysNotice{
/**
     * 公告ID
     */
    private Integer noticeId;
/**
     * 公告标题
     */
    private String noticeTitle;
/**
     * 公告类型（0 通知 1 公告）
     */
    private String noticeType;
/**
     * 公告内容
     */
    private String noticeContent;
/**
     * 公告状态（0正常 1关闭）
     */
    private String status;
/**
     * 创建者
     */
    private String createBy;
/**
     * 创建时间
     */
    private LocalDateTime createTime;
/**
     * 更新者
     */
    private String updateBy;
/**
     * 更新时间
     */
    private LocalDateTime updateTime;
/**
     * 备注
     */
    private String remark;
}

