package ink.afro.mapper;

import ink.afro.entity.SysNotice;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 通知公告表(SysNotice)表数据库访问层
 *
 * @author goodboy95-code
 */
public interface SysNoticeMapper {

    /**
     * 新增数据
     *
     * @param sysNotice 实例对象
     * @return 影响行数
     */
    int insert(SysNotice sysNotice);

    /**
     * 批量新增数据
     *
     * @param entities List<SysNotice> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysNotice> entities);

    /**
     * 批量新增或按主键更新数据
     *
     * @param entities List<SysNotice> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysNotice> entities);

    /**
     * 通过主键删除数据
     *
     * @param noticeId 主键
     * @return 影响行数
     */
    int deleteById(Integer noticeId);

    /**
     * 修改数据
     *
     * @param sysNotice 实例对象
     * @return 影响行数
     */
    int update(SysNotice sysNotice);

    /**
     * 通过 noticeId 查询单条数据
     *
     * @param noticeId 主键
     * @return 实例对象
     */
    SysNotice queryById(Integer noticeId);

    /**
     * 查询所有行数据
     *
     * @return 对象列表
     */
    List<SysNotice> queryAll();

    /**
     * 查询指定行数据
     *
     * @param sysNotice 查询条件
     * @param pageable  分页对象
     * @return 对象列表
     */
    List<SysNotice> queryAllByLimit(SysNotice sysNotice, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param sysNotice 查询条件
     * @return 总行数
     */
    long count(SysNotice sysNotice);
}

