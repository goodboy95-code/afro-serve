package ink.afro.mapper;

import ink.afro.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 用户信息表(SysUser)表数据库访问层
 *
 * @author goodboy95-code
 */
public interface SysUserMapper {

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 影响行数
     */
    int insert(SysUser sysUser);

    /**
     * 批量新增数据
     *
     * @param entities List<SysUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysUser> entities);

    /**
     * 批量新增或按主键更新数据
     *
     * @param entities List<SysUser> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysUser> entities);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(Long userId);

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 影响行数
     */
    int update(SysUser sysUser);

    /**
     * 通过 userId 查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    SysUser queryById(Long userId);

    /**
     * 通过userName查询单条数据
     *
     * @param userName 名称
     * @return 实例对象
     */
    SysUser queryByName(String userName);
    /**
     * 校验手机号码是否唯一
     *
     * @param phoneNumber 手机号码
     * @return 结果
     */
    SysUser queryByPhoneNumber(String phoneNumber);

    /**
     * 校验email是否唯一
     *
     * @param email 用户邮箱
     * @return 结果
     */
    SysUser queryByEmail(String email);

    /**
     * 查询所有行数据
     *
     * @return 对象列表
     */
    List<SysUser> queryAll();

    /**
     * 查询指定行数据
     *
     * @param sysUser  查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<SysUser> queryAllByLimit(SysUser sysUser, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param sysUser 查询条件
     * @return 总行数
     */
    long count(SysUser sysUser);

    /**
     * 通过部门ID统计总行数
     *
     * @param departmentId  部门ID
     * @param sysUser 查询条件
     * @return 对象列表
     */
    long queryCountByDepartmentId(Long departmentId, SysUser sysUser);

    /**
     * 通过部门ID查询数据
     *
     * @param departmentId   部门ID
     * @param pageable – 分页对象
     * @return 对象列表
     */
    List<SysUser> queryByDepartmentIdByLimit(Long departmentId, @Param("sysUser") SysUser sysUser, @Param("pageable") Pageable pageable);
}

