package ink.afro.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 角色信息表(SysRole)实体类
 *
 * @author goodboy95-code
 */
@Data
public class SysRole {
    /**
     * 角色ID
     */
    private Integer roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 显示顺序
     */
    private Integer roleSort;
    /**
     * 角色状态（0 正常，1 停用）
     */
    private String status;
    /**
     * 删除标志（0 代表存在，1 代表删除）
     */
    private String delFlag;
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

