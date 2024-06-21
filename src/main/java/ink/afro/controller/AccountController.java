package ink.afro.controller;

import ink.afro.entity.Account;
import ink.afro.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * (Account)表控制层
 *
 * @author goodboy95-code
 */
@RestController
@RequestMapping("account")
public class AccountController {
    /**
     * 服务对象
     */
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 新增数据
     *
     * @param account 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public ResponseEntity<Account> add(@RequestBody Account account) {
        return ResponseEntity.ok(this.accountService.insert(account));
    }

    /**
     * 删除数据
     *
     * @param accountId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById")
    public ResponseEntity<Boolean> deleteById(Integer accountId) {
        return ResponseEntity.ok(this.accountService.deleteById(accountId));
    }

    /**
     * 编辑数据
     *
     * @param account 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public ResponseEntity<Account> edit(@RequestBody Account account) {
        return ResponseEntity.ok(this.accountService.update(account));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param accountId 主键
     * @return 单条数据
     */
    @GetMapping("/queryById")
    public ResponseEntity<Account> queryById(Integer accountId) {
        return ResponseEntity.ok(this.accountService.queryById(accountId));
    }

    /**
     * 分页查询
     *
     * @param account     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping("/queryByPage")
    public ResponseEntity<Page<Account>> queryByPage(Account account, PageRequest pageRequest) {
        return ResponseEntity.ok(this.accountService.queryByPage(account, pageRequest));
    }

    @GetMapping("/demo")
    public void demo() {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.shutdown(); // 关闭线程池
        executor.submit(() -> System.out.println("执行任务"));
    }
}

