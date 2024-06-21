package ink.afro.service;

import ink.afro.entity.SysNotice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 通知公告表(SysNotice)表服务接口
 *
 * @author goodboy95-code
 * @since 2024-03-17 20:33:13
 */
public interface SysNoticeService {

    /**
     * 新增数据
     *
     * @param sysNotice 实例对象
     * @return 实例对象
     */
    boolean insert(SysNotice sysNotice);

    /**
     * 通过主键删除数据
     *
     * @param noticeId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer noticeId);

    /**
     * 修改数据
     *
     * @param sysNotice 实例对象
     * @return 实例对象
     */
    SysNotice update(SysNotice sysNotice);

    /**
     * 通过ID查询单条数据
     *
     * @param noticeId 主键
     * @return 实例对象
     */
    SysNotice queryById(Integer noticeId);

    /**
     * 分页查询
     *
     * @param sysNotice 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<SysNotice> queryByPage(SysNotice sysNotice, PageRequest pageRequest);
}
