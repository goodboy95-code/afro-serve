package ink.afro.service;

import ink.afro.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Account)表服务接口
 *
 * @author goodboy95-code
 * @since 2024-03-20 16:49:29
 */
public interface AccountService {

    /**
     * 新增数据
     *
     * @param account 实例对象
     * @return 实例对象
     */
    Account insert(Account account);

    /**
     * 通过主键删除数据
     *
     * @param accountId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer accountId);

    /**
     * 修改数据
     *
     * @param account 实例对象
     * @return 实例对象
     */
    Account update(Account account);

    /**
     * 通过ID查询单条数据
     *
     * @param accountId 主键
     * @return 实例对象
     */
    Account queryById(Integer accountId);

    /**
     * 通过name查询单条数据
     *
     * @param accountName 名称
     * @return 实例对象
     */
    Account queryByName(String accountName);

    /**
     * 分页查询
     *
     * @param account     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Account> queryByPage(Account account, PageRequest pageRequest);
}
