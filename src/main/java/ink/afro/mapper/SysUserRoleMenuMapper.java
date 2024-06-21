package ink.afro.mapper;

import ink.afro.entity.SysUserRoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户、角色和快捷导航（菜单）关联表(SysUserRoleMenu)表数据库访问层
 *
 * @author goodboy95-code
 */
public interface SysUserRoleMenuMapper {

    /**
     * 批量新增数据
     *
     * @param entities Set<SysUserRoleMenu> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysUserRoleMenu> entities);

    /**
     * 批量删除数据
     *
     * @param entities Set<SysUserRoleMenu> 实例对象列表
     * @return 影响行数
     */
    int deleteBatch(@Param("entities") List<SysUserRoleMenu> entities);

    /**
     * 清空表数据
     */
    void truncateData();

    /**
     * 查询所有行数据
     *
     * @return 对象列表
     */
    List<SysUserRoleMenu> queryAll();

    /**
     * 通过用户角色ID查询行数据
     *
     * @param userId 用户ID
     * @param roleId 角色ID
     * @return 对象列表
     */
    List<SysUserRoleMenu> queryByUserIdAndRoleId(@Param("userId") Long userId, @Param("roleId") Integer roleId);

    /**
     * 统计总行数
     *
     * @param sysUserRoleMenu 查询条件
     * @return 总行数
     */
    long count(SysUserRoleMenu sysUserRoleMenu);
}

