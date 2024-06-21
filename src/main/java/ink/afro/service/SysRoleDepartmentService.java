package ink.afro.service;

import ink.afro.entity.SysRoleDepartment;

import java.util.List;

/**
 * 角色和部门关联表(SysRoleDepartment)表服务接口
 *
 * @author goodboy95-code
 * @since 2023-12-30 03:56:32
 */
public interface SysRoleDepartmentService {

    /**
     * 批量新增数据
     *
     * @param sysRoleDepartment 实例对象
     * @return 添加成功行数
     */
    int insertBatch(List<SysRoleDepartment> sysRoleDepartment);

    /**
     * 批量删除数据
     *
     * @param sysRoleDepartment 实例对象集合
     * @return 删除成功行数
     */
    int deleteBatch(List<SysRoleDepartment> sysRoleDepartment);
    /**
     * 清空数据
     *
     */
    void truncateData();

    /**
     * 通过角色id查询
     *
     * @param roleId 角色Id
     * @return 数据集合
     */
    List<SysRoleDepartment> queryByRoleId(Integer roleId);
}
