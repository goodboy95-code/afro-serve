package ink.afro.service;

import ink.afro.entity.SysOperateLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 操作日志记录表(SysOperateLog)表服务接口
 *
 * @author goodboy95-code
 * @since 2024-03-16 11:54:37
 */
public interface SysOperateLogService {

    /**
     * 新增数据
     *
     * @param sysOperateLog 实例对象
     * @return 实例对象
     */
    boolean insert(SysOperateLog sysOperateLog);

    /**
     * 通过主键删除数据
     *
     * @param operateLogId 主键
     * @return 是否成功
     */
    boolean deleteById(Long operateLogId);

    /**
     * 删除多条数据
     *
     * @param operateLogIds 多个主键
     * @return 是否成功
     */
    boolean deleteBatch(List<Long> operateLogIds);

    /**
     * 清空数据
     *
     * @return 是否成功
     */
    boolean deleteAll();

    /**
     * 修改数据
     *
     * @param sysOperateLog 实例对象
     * @return 实例对象
     */
    SysOperateLog update(SysOperateLog sysOperateLog);

    /**
     * 通过ID查询单条数据
     *
     * @param operateLogId 主键
     * @return 实例对象
     */
    SysOperateLog queryById(Long operateLogId);

    /**
     * 查询所有数据
     *
     * @return 实例对象
     */
    List<SysOperateLog> queryAll();

    /**
     * 分页查询
     *
     * @param sysOperateLog 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
    Page<SysOperateLog> queryByPage(SysOperateLog sysOperateLog, PageRequest pageRequest);
}
