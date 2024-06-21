package ink.afro.service;

import ink.afro.entity.SysLoginLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 系统访问记录(SysLoginLog)表服务接口
 *
 * @author goodboy95-code
 * @since 2024-03-18 19:11:53
 */
public interface SysLoginLogService {

    /**
     * 新增数据
     *
     * @param sysLoginLog 实例对象
     * @return 实例对象
     */
    boolean insert(SysLoginLog sysLoginLog);

    /**
     * 通过主键删除数据
     *
     * @param loginLogId 主键
     * @return 是否成功
     */
    boolean deleteById(Long loginLogId);


    /**
     * 通过主键列表删除数据
     *
     * @param loginLogIds 多个主键
     * @return 是否成功
     */
    boolean deleteBatch(List<Long> loginLogIds);

    /**
     * 清空
     *
     * @return 是否成功
     */
    boolean deleteAll();

    /**
     * 修改数据
     *
     * @param sysLoginLog 实例对象
     * @return 实例对象
     */
    SysLoginLog update(SysLoginLog sysLoginLog);

    /**
     * 通过ID查询单条数据
     *
     * @param loginLogId 主键
     * @return 实例对象
     */
    SysLoginLog queryById(Long loginLogId);

    /**
     * 查询所有数据
     *
     * @return 实例对象
     */
    List<SysLoginLog> queryAll();

    /**
     * 分页查询
     *
     * @param sysLoginLog 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<SysLoginLog> queryByPage(SysLoginLog sysLoginLog, PageRequest pageRequest);
}
