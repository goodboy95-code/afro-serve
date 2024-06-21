package ink.afro.mapper;

import ink.afro.entity.SysLoginLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 系统访问记录(SysLoginLog)表数据库访问层
 *
 * @author goodboy95-code
 */
public interface SysLoginLogMapper {

    /**
     * 新增数据
     *
     * @param sysLoginLog 实例对象
     * @return 影响行数
     */
    int insert(SysLoginLog sysLoginLog);

    /**
     * 批量新增数据
     *
     * @param entities List<SysLoginLog> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysLoginLog> entities);

    /**
     * 批量新增或按主键更新数据
     *
     * @param entities List<SysLoginLog> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysLoginLog> entities);

    /**
     * 通过主键删除数据
     *
     * @param loginLogId 主键
     * @return 影响行数
     */
    int deleteById(Long loginLogId);

    /**
     * 通过主键批量删除数据
     *
     * @param loginLogIds 多个主键
     * @return 影响行数
     */
    int deleteBatch(List<Long> loginLogIds);

    /**
     * 清空
     *
     * @return 影响行数
     */
    int deleteAll();
    
   /**
     * 修改数据
     *
     * @param sysLoginLog 实例对象
     * @return 影响行数
     */
    int update(SysLoginLog sysLoginLog);
    
    /**
     * 通过 loginLogId 查询单条数据
     *
     * @param loginLogId 主键
     * @return 实例对象
     */
    SysLoginLog queryById(Long loginLogId);
    
   /**
     * 查询所有行数据
     *
     * @return 对象列表
     */
    List<SysLoginLog> queryAll();

    /**
     * 查询指定行数据
     *
     * @param sysLoginLog 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<SysLoginLog> queryAllByLimit(SysLoginLog sysLoginLog, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param sysLoginLog 查询条件
     * @return 总行数
     */
    long count(SysLoginLog sysLoginLog);
}

