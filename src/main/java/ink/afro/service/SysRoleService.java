package ink.afro.service;

import ink.afro.entity.SysRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 角色信息表(SysRole)表服务接口
 *
 * @author goodboy95-code
 * @since 2024-01-01 21:43:49
 */
public interface SysRoleService {

    /**
     * 新增数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    boolean insert(SysRole sysRole);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer roleId);

    /**
     * 修改数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    SysRole update(SysRole sysRole);

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    SysRole queryById(Integer roleId);

    /**
     * 通过name查询单条数据
     *
     * @param roleName 名称
     * @return 实例对象
     */
    SysRole queryByName(String roleName);

    /**
     * 分页查询
     *
     * @param sysRole     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<SysRole> queryByPage(SysRole sysRole, PageRequest pageRequest);

    /**
     * 通过用户ID获取用户的角色数据
     *
     * @param userId 用户ID
     * @return List<SysRole> 角色集合
     */
    List<SysRole> queryByUserId(Long userId);

    /**
     * 获取所有角色
     *
     * @return List<SysRole> 角色集合
     */
    List<SysRole> queryAll();

    /**
     * 获取角色数据权限
     *
     * @param userId 用户信息
     * @return 角色信息
     */
    List<SysRole> queryRoleByUserId(Long userId);
}
