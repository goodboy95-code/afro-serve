package ink.afro.service.impl;

import ink.afro.entity.Account;
import ink.afro.mapper.AccountMapper;
import ink.afro.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * (Account)表服务实现类
 *
 * @author goodboy95-code
 * @since 2024-03-20 16:49:29
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    private final AccountMapper accountMapper;

    @Autowired
    public AccountServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    @Transactional
    public Account insert(Account account) {
        accountMapper.insert(account);
        try {
            insertOne();
        } catch (RuntimeException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public boolean deleteById(Integer accountId) {
        return this.accountMapper.deleteById(accountId) > 0;
    }

    @Override
    public Account update(Account account) {
        this.accountMapper.update(account);
        return this.queryById(account.getAccountId());
    }

    @Override
    public Account queryById(Integer accountId) {
        return this.accountMapper.queryById(accountId);
    }

    @Override
    public Account queryByName(String accountName) {
        return this.accountMapper.queryByName(accountName);
    }

    @Override
    public Page<Account> queryByPage(Account account, PageRequest pageRequest) {
        long total = this.accountMapper.count(account);
        return new PageImpl<>(this.accountMapper.queryAllByLimit(account, pageRequest), pageRequest, total);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertOne() throws RuntimeException {
        Account account11 = new Account();
        account11.setAccountName(13579);
        account11.setMoney("245789");
        this.accountMapper.insert(account11);
        boolean flag = true;
        if (flag) {
            throw new RuntimeException();
        }
        Account account111 = new Account();
        account111.setAccountName(1234567);
        account111.setMoney("1234567");
        this.accountMapper.insert(account111);
    }
}


