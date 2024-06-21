package ink.afro.service;

import ink.afro.entity.SysUserRole;

import java.util.List;

/**
 * 用户和角色关联表(SysUserRole)表服务接口
 *
 * @author goodboy95-code
 * @since 2023-12-30 03:56:33
 */
public interface SysUserRoleService {

    /**
     * 批量新增数据
     *
     * @param sysUserRoleList 用户角色条件参数
     * @return 添加成功行数
     */
    int insertBatch(List<SysUserRole> sysUserRoleList);

    /**
     * 批量删除数据
     *
     * @param sysUserRoleList 用户角色条件参数
     * @return 删除成功行数
     */
    int deleteBatch(List<SysUserRole> sysUserRoleList);

    /**
     * 清空数据
     */
    void truncateData();

    /**
     * 通过角色id获取数据
     *
     * @param userId 用户id
     * @return 数据集合
     */
    List<SysUserRole> queryByUserId(Long userId);
}
