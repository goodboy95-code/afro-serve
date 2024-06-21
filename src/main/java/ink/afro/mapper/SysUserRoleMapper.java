package ink.afro.mapper;

import ink.afro.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户和角色关联表(SysUserRole)表数据库访问层
 *
 * @author goodboy95-code
 */
public interface SysUserRoleMapper {

    /**
     * 批量新增数据
     *
     * @param entities Set<SysUserRole> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysUserRole> entities);

    /**
     * 批量删除数据
     *
     * @param entities Set<SysUserRole> 实例对象列表
     * @return 影响行数
     */
    int deleteBatch(@Param("entities") List<SysUserRole> entities);

    /**
     * 清空数据
     */
    void truncateData();

    /**
     * 通过用户ID查询行数据
     *
     * @param userId 用户ID
     * @return 对象列表
     */
    List<SysUserRole> queryByUserId(Long userId);

    /**
     * 查询所有行数据
     *
     * @return 对象列表
     */
    List<SysUserRole> queryAll();

    /**
     * 统计总行数
     *
     * @param sysUserRole 查询条件
     * @return 总行数
     */
    long count(SysUserRole sysUserRole);
}

