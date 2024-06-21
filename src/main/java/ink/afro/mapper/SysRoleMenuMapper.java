package ink.afro.mapper;

import ink.afro.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色和菜单关联表(SysRoleMenu)表数据库访问层
 *
 * @author goodboy95-code
 */
public interface SysRoleMenuMapper {

    /**
     * 批量新增数据
     *
     * @param entities Set<SysRoleMenu> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysRoleMenu> entities);

    /**
     * 批量删除数据
     *
     * @param entities Set<SysRoleMenu> 实例对象列表
     * @return 影响行数
     */
    int deleteBatch(@Param("entities") List<SysRoleMenu> entities);

    /**
     * 清空数据
     */
    void truncateData();

    /**
     * 查询所有行数据
     *
     * @return 对象列表
     */
    List<SysRoleMenu> queryAll();

    /**
     * 通过角色ID查询行数据
     *
     * @param roleId 角色ID
     * @return 对象列表
     */
    List<SysRoleMenu> queryByRoleId(Integer roleId);

    /**
     * 统计总行数
     *
     * @param sysRoleMenu 查询条件
     * @return 总行数
     */
    long count(SysRoleMenu sysRoleMenu);
}

