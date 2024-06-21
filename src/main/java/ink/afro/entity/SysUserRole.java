package ink.afro.entity;

import lombok.Data;

/**
 * 用户和角色关联表(SysUserRole)实体类
 *
 * @author goodboy95-code
 */
@Data
public class SysUserRole {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Integer roleId;

    public SysUserRole(Long userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}

