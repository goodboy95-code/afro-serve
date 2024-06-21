package ink.afro.mapper;

import ink.afro.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 角色信息表(SysRole)表数据库访问层
 *
 * @author goodboy95-code
 */
public interface SysRoleMapper {

    /**
     * 新增数据
     *
     * @param sysRole 实例对象
     * @return 影响行数
     */
    int insert(SysRole sysRole);

    /**
     * 批量新增数据
     *
     * @param entities List<SysRole> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysRole> entities);

    /**
     * 批量新增或按主键更新数据
     *
     * @param entities List<SysRole> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysRole> entities);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 影响行数
     */
    int deleteById(Integer roleId);

    /**
     * 修改数据
     *
     * @param sysRole 实例对象
     * @return 影响行数
     */
    int update(SysRole sysRole);

    /**
     * 通过 roleId 查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    SysRole queryById(Integer roleId);

    /**
     * 通过sysRoleName查询单条数据
     *
     * @param sysRoleName 名称
     * @return 实例对象
     */
    SysRole queryByName(String sysRoleName);

    /**
     * 查询所有行数据
     *
     * @return 对象列表
     */
    List<SysRole> queryAll();

    /**
     * 查询指定行数据
     *
     * @param sysRole  查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<SysRole> queryAllByLimit(SysRole sysRole, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param sysRole 查询条件
     * @return 总行数
     */
    long count(SysRole sysRole);

    /**
     * 根据用户ID获取角色
     *
     * @param userId 用户ID
     * @return List<SysRole> 角色集合
     */
    List<SysRole> queryByUserId(Long userId);
}

