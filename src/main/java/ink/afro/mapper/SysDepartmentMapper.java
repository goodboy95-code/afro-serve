package ink.afro.mapper;

import ink.afro.entity.SysDepartment;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 部门表(SysDepartment)表数据库访问层
 *
 * @author goodboy95-code
 */
public interface SysDepartmentMapper {

    /**
     * 新增数据
     *
     * @param sysDepartment 实例对象
     * @return 影响行数
     */
    int insert(SysDepartment sysDepartment);

    /**
     * 批量新增数据
     *
     * @param entities List<SysDepartment> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysDepartment> entities);

    /**
     * 批量新增或按主键更新数据
     *
     * @param entities List<SysDepartment> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysDepartment> entities);

    /**
     * 通过主键删除数据
     *
     * @param departmentId 主键
     * @return 影响行数
     */
    int deleteById(Long departmentId);

    /**
     * 修改数据
     *
     * @param sysDepartment 实例对象
     * @return 影响行数
     */
    int update(SysDepartment sysDepartment);

    /**
     * 通过 departmentId 查询单条数据
     *
     * @param departmentId 主键
     * @return 实例对象
     */
    SysDepartment queryById(Long departmentId);

    /**
     * 通过sysDepartmentName查询单条数据
     *
     * @param sysDepartmentName 名称
     * @return 实例对象
     */
    SysDepartment queryByName(String sysDepartmentName);

    /**
     * 查询所有行数据
     *
     * @return 对象列表
     */
    List<SysDepartment> queryAll();

    /**
     * 查询指定行数据
     *
     * @param sysDepartment  查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<SysDepartment> queryAllByLimit(SysDepartment sysDepartment, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param sysDepartment 查询条件
     * @return 总行数
     */
    long count(SysDepartment sysDepartment);

    /**
     * 根据角色ID查询部门
     *
     * @param roleId 角色ID
     * @return 对象列表
     */
    List<SysDepartment> queryByRoleId(Integer roleId);
}

