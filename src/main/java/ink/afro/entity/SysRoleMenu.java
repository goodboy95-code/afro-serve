package ink.afro.entity;

import lombok.Data;

/**
 * 角色和菜单关联表(SysRoleMenu)实体类
 *
 * @author goodboy95-code
 */
@Data
public class SysRoleMenu {
    /**
     * 角色ID
     */
    private Integer roleId;
    /**
     * 菜单ID
     */
    private Integer menuId;
}

