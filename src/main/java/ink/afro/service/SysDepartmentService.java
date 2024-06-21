package ink.afro.service;

import ink.afro.entity.SysDepartment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 部门表(SysDepartment)表服务接口
 *
 * @author goodboy95-code
 * @since 2024-01-01 21:43:48
 */
public interface SysDepartmentService {

    /**
     * 新增数据
     *
     * @param sysDepartment 实例对象
     * @return 实例对象
     */
    boolean insert(SysDepartment sysDepartment);

    /**
     * 通过主键删除数据
     *
     * @param departmentId 主键
     * @return 是否成功
     */
    boolean deleteById(Long departmentId);

    /**
     * 修改数据
     *
     * @param sysDepartment 实例对象
     * @return 实例对象
     */
    SysDepartment update(SysDepartment sysDepartment);

    /**
     * 通过ID查询单条数据
     *
     * @param departmentId 主键
     * @return 实例对象
     */
    SysDepartment queryById(Long departmentId);

    /**
     * 查询所有数据
     *
     * @return 部门集合
     */
    List<SysDepartment> queryAll();

    /**
     * 通过name查询单条数据
     *
     * @param departmentName 名称
     * @return 实例对象
     */
    SysDepartment queryByName(String departmentName);

    /**
     * 分页查询
     *
     * @param sysDepartment     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<SysDepartment> queryByPage(SysDepartment sysDepartment, PageRequest pageRequest);

    /**
     * 查询部门树信息
     *
     * @return 部门集合
     */
    List<SysDepartment> queryDepartmentTree();

    /**
     * 根据用户ID，角色ID查询部门树信息
     *
     * @param userId 用户ID
     * @return 部门集合
     */
    List<SysDepartment> queryDepartmentTreeByRoleId(Long userId, Integer roleId);
}
