package ink.afro.entity;

import lombok.Data;

/**
 * 角色和部门关联表(SysRoleDepartment)实体类
 *
 * @author goodboy95-code
 */
@Data
public class SysRoleDepartment {
    /**
     * 角色ID
     */
    private Integer roleId;
    /**
     * 部门ID
     */
    private Integer departmentId;
}

