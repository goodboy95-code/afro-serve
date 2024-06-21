package ink.afro.service;

import ink.afro.entity.SysUserRoleMenu;

import java.util.List;

/**
 * 用户、角色和快捷导航（菜单）关联表(SysUserRoleMenu)表服务接口
 *
 * @author goodboy95-code
 * @since 2024-01-03 04:32:10
 */
public interface SysUserRoleMenuService {

    /**
     * 批量新增数据
     *
     * @param sysUserRoleMenu 实例对象
     * @return 添加成功行数
     */
    int insertBatch(List<SysUserRoleMenu> sysUserRoleMenu);

    /**
     * 批量删除数据
     *
     * @param sysUserRoleMenu 实例对象
     * @return 删除成功行数
     */
    int deleteBatch(List<SysUserRoleMenu> sysUserRoleMenu);

    /**
     * 清空数据
     *
     */
    void truncateData();

    /**
     * 通过用户角色ID查询行数据
     *
     * @param userId 用户ID
     * @param roleId 角色ID
     * @return 数据集合
     */
    List<SysUserRoleMenu> queryByUserIdAndRoleId(Long userId, Integer roleId);
}
