package ink.afro.service;

import ink.afro.entity.SysRoleMenu;

import java.util.List;

/**
 * 角色和菜单关联表(SysRoleMenu)表服务接口
 *
 * @author goodboy95-code
 * @since 2023-12-30 03:56:32
 */
public interface SysRoleMenuService {

    /**
     * 批量新增数据
     *
     * @param sysRoleMenu 实例对象
     * @return 添加成功行数
     */
    int insertBatch(List<SysRoleMenu> sysRoleMenu);

    /**
     * 批量删除数据
     *
     * @param sysRoleMenu 实例对象集合
     * @return 删除成功行数
     */
    int deleteBatch(List<SysRoleMenu> sysRoleMenu);

    /**
     * 清空数据
     *
     */
    void truncateData();

    /**
     * 通过角色id获取数据
     *
     * @param roleId 角色id
     * @return 数据集合
     */
    List<SysRoleMenu> queryByRoleId(Integer roleId);
}
