package ink.afro.service;

import ink.afro.entity.SysMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Set;

/**
 * 菜单权限表(SysMenu)表服务接口
 *
 * @author goodboy95-code
 * @since 2024-01-01 21:43:48
 */
public interface SysMenuService {

    /**
     * 新增数据
     *
     * @param sysMenu 实例对象
     * @return 实例对象
     */
    boolean insert(SysMenu sysMenu);

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer menuId);

    /**
     * 修改数据
     *
     * @param sysMenu 实例对象
     * @return 实例对象
     */
    SysMenu update(SysMenu sysMenu);

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    SysMenu queryById(Integer menuId);

    /**
     * 通过name查询单条数据
     *
     * @param menuName 名称
     * @return 实例对象
     */
    SysMenu queryByName(String menuName);

    /**
     * 分页查询
     *
     * @param sysMenu     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<SysMenu> queryByPage(SysMenu sysMenu, PageRequest pageRequest);

    /**
     * 查询菜单树信息
     *
     * @return 菜单集合
     */
    List<SysMenu> queryMenuTree();

    /**
     * 查询菜单树信息
     *
     * @return 菜单集合
     */
    SysMenu queryMenuTreeBDM();

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param userId 用户ID
     * @return 菜单集合
     */
    List<SysMenu> queryMenuTreeByRoleId(Long userId, Integer roleId);

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     * @return 菜单集合
     */
    SysMenu queryMenuTreeBDMByRoleId(Integer roleId);

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限集合
     */
    List<String> querySysMenuPermsByUserId(Long userId);

    /**
     * 根据角色ID查询权限
     *
     * @param roleId 角色ID
     * @return 权限集合
     */
    List<String> querySysMenuPermsByRoleId(Integer roleId);

    /**
     * 根据用户角色获取菜单
     *
     * @param userId 用户ID
     * @param roleId 角色ID
     * @return 菜单集合
     */
    List<SysMenu> querySysMenusByUserIdAndRoleId(Long userId, Integer roleId);

    /**
     * 根据路由路径集合查询菜单ID
     *
     * @param routePath 路由路径集合
     * @return 菜单ID集合
     */
    Set<Integer> querySysMenuIdsByPaths(Set<String> routePath);
}
