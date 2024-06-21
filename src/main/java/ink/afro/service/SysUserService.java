package ink.afro.service;

import ink.afro.entity.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 用户信息表(SysUser)表服务接口
 *
 * @author goodboy95-code
 * @since 2024-01-01 21:43:49
 */
public interface SysUserService {

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    boolean insert(SysUser sysUser);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(Long userId);

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    boolean update(SysUser sysUser);

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    SysUser queryById(Long userId);

    /**
     * 通过name查询单条数据
     *
     * @param userName 名称
     * @return 实例对象
     */
    SysUser queryByName(String userName);

    /**
     * 分页查询
     *
     * @param sysUser     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<SysUser> queryByPage(SysUser sysUser, PageRequest pageRequest);

    /**
     * 通过部门ID分页查询
     *
     * @param departmentId      部门ID
     * @param sysUser     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<SysUser> queryByDepartmentId(Long departmentId, SysUser sysUser, PageRequest pageRequest);
    /**
     * 校验用户名称是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    boolean isUniqueUsername(SysUser user);
    /**
     * 校验手机号码是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    boolean isUniquePhoneNumber(SysUser user);
    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    boolean isUniqueEmail(SysUser user);
}
