package ink.afro.entity;

import lombok.Data;

/**
 * 用户、角色和快捷导航（菜单）关联表(SysUserRoleMenu)实体类
 *
 * @author goodboy95-code
 */
@Data
public class SysUserRoleMenu {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Integer roleId;
    /**
     * 菜单ID
     */
    private Integer menuId;
}

