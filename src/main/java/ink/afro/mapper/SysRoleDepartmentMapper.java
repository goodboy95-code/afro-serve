package ink.afro.mapper;

import ink.afro.entity.SysRoleDepartment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色和部门关联表(SysRoleDepartment)表数据库访问层
 *
 * @author goodboy95-code
 */
public interface SysRoleDepartmentMapper {

    /**
     * 批量新增数据
     *
     * @param entities Set<SysRoleDepartment> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysRoleDepartment> entities);

    /**
     * 批量删除数据
     *
     * @param entities Set<SysRoleDepartment> 实例对象列表
     * @return 影响行数
     */
    int deleteBatch(@Param("entities") List<SysRoleDepartment> entities);

    /**
     * 清空数据
     */
    void truncateData();

    /**
     * 查询所有行数据
     *
     * @return 对象列表
     */
    List<SysRoleDepartment> queryAll();

    /**
     * 通过角色查询行数据
     *
     * @return 对象列表
     */
    List<SysRoleDepartment> queryByRoleId(Integer roleId);

    /**
     * 统计总行数
     *
     * @param sysRoleDepartment 查询条件
     * @return 总行数
     */
    long count(SysRoleDepartment sysRoleDepartment);
}

