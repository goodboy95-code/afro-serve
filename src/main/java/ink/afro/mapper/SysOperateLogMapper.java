package ink.afro.mapper;

import ink.afro.entity.SysOperateLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 操作日志记录表(SysOperateLog)表数据库访问层
 *
 * @author goodboy95-code
 */
public interface SysOperateLogMapper {

    /**
     * 新增数据
     *
     * @param sysOperateLog 实例对象
     * @return 影响行数
     */
    int insert(SysOperateLog sysOperateLog);

    /**
     * 批量新增数据
     *
     * @param entities List<SysOperateLog> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysOperateLog> entities);

    /**
     * 批量新增或按主键更新数据
     *
     * @param entities List<SysOperateLog> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysOperateLog> entities);

    /**
     * 通过主键删除数据
     *
     * @param operateLogId 主键
     * @return 影响行数
     */
    int deleteById(Long operateLogId);

    /**
     * 通过主键批量删除数据
     *
     * @param operateLogIds 多个主键
     * @return 影响行数
     */
    int deleteBatch(List<Long> operateLogIds);

    /**
     * 通过主键批量删除数据
     *
     * @return 影响行数
     */
    int deleteAll();
    
   /**
     * 修改数据
     *
     * @param sysOperateLog 实例对象
     * @return 影响行数
     */
    int update(SysOperateLog sysOperateLog);
    
    /**
     * 通过 operateLogId 查询单条数据
     *
     * @param operateLogId 主键
     * @return 实例对象
     */
    SysOperateLog queryById(Long operateLogId);
    
   /**
     * 查询所有行数据
     *
     * @return 对象列表
     */
    List<SysOperateLog> queryAll();

    /**
     * 查询指定行数据
     *
     * @param sysOperateLog 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<SysOperateLog> queryAllByLimit(SysOperateLog sysOperateLog, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param sysOperateLog 查询条件
     * @return 总行数
     */
    long count(SysOperateLog sysOperateLog);
}

