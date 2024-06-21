package ink.afro.mapper;

import ink.afro.entity.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * 菜单权限表(SysMenu)表数据库访问层
 *
 * @author goodboy95-code
 */
public interface SysMenuMapper {

    /**
     * 新增数据
     *
     * @param sysMenu 实例对象
     * @return 影响行数
     */
    int insert(SysMenu sysMenu);

    /**
     * 批量新增数据
     *
     * @param entities List<SysMenu> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysMenu> entities);

    /**
     * 批量新增或按主键更新数据
     *
     * @param entities List<SysMenu> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysMenu> entities);

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 影响行数
     */
    int deleteById(Integer menuId);

    /**
     * 修改数据
     *
     * @param sysMenu 实例对象
     * @return 影响行数
     */
    int update(SysMenu sysMenu);

    /**
     * 通过 menuId 查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    SysMenu queryById(Integer menuId);

    /**
     * 通过sysMenuName查询单条数据
     *
     * @param sysMenuName 名称
     * @return 实例对象
     */
    SysMenu queryByName(String sysMenuName);

    /**
     * 查询所有行数据
     *
     * @return 对象列表
     */
    List<SysMenu> queryAll();

    /**
     * 查询指定行数据
     *
     * @param sysMenu  查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<SysMenu> queryAllByLimit(SysMenu sysMenu, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param sysMenu 查询条件
     * @return 总行数
     */
    long count(SysMenu sysMenu);

    /**
     * 根据角色ID查询菜单
     *
     * @param routePaths 路由路径
     * @return List<SysMenu> 菜单集合
     */
    List<SysMenu> queryByPaths(Set<String> routePaths);

    /**
     * 根据角色ID查询菜单
     *
     * @param roleId 角色ID
     * @return List<SysMenu> 菜单集合
     */
    List<SysMenu> queryByRoleId(Integer roleId);

    /**
     * 根据角色ID查询BDM菜单
     *
     * @param roleId 角色ID
     * @return List<SysMenu> 菜单集合
     */
    List<SysMenu> queryBDMByRoleId(Integer roleId);

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return list<String> 权限集合
     */
    List<String> queryPermsByUserId(Long userId);

    /**
     * 根据角色ID查询权限
     *
     * @param roleId 角色ID
     * @return list<String> 权限集合
     */
    List<String> queryPermsByRoleId(Integer roleId);

    /**
     * 根据用户ID和角色ID获取菜单
     *
     * @param userId 用户ID
     * @param roleId 角色ID
     * @return List<SysMenu> 菜单集合
     */
    List<SysMenu> queryByUserIdAndRoleId(Long userId, Integer roleId);
}

