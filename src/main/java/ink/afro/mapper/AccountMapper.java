package ink.afro.mapper;

import ink.afro.entity.Account;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (Account)表数据库访问层
 *
 * @author goodboy95-code
 */
public interface AccountMapper {

    /**
     * 新增数据
     *
     * @param account 实例对象
     * @return 影响行数
     */
    int insert(Account account);

    /**
     * 批量新增数据
     *
     * @param entities List<Account> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Account> entities);

    /**
     * 批量新增或按主键更新数据
     *
     * @param entities List<Account> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Account> entities);

    /**
     * 通过主键删除数据
     *
     * @param accountId 主键
     * @return 影响行数
     */
    int deleteById(Integer accountId);

    /**
     * 修改数据
     *
     * @param account 实例对象
     * @return 影响行数
     */
    int update(Account account);

    /**
     * 通过 accountId 查询单条数据
     *
     * @param accountId 主键
     * @return 实例对象
     */
    Account queryById(Integer accountId);

    /**
     * 通过accountName查询单条数据
     *
     * @param accountName 名称
     * @return 实例对象
     */
    Account queryByName(String accountName);

    /**
     * 查询所有行数据
     *
     * @return 对象列表
     */
    List<Account> queryAll();

    /**
     * 查询指定行数据
     *
     * @param account  查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Account> queryAllByLimit(Account account, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param account 查询条件
     * @return 总行数
     */
    long count(Account account);
}

