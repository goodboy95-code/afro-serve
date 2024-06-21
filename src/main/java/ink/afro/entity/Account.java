package ink.afro.entity;

import lombok.Data;

import java.util.Objects;

/**
 * (Account)实体类
 *
 * @author goodboy95-code
 */
@Data
public class Account {

    private Integer accountId;

    private Integer accountName;

    private String money;

    private Demo demo;

    public Account() {
    }

    public Account(Integer accountId, Integer accountName, String money, Demo demo) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.money = money;
        this.demo = demo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountId, account.accountId) && Objects.equals(accountName, account.accountName) && Objects.equals(money, account.money) && Objects.equals(demo, account.demo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, accountName, money, demo);
    }

    public static void main(String[] args) {
        Account account = new Account(1, 1, "100", new Demo("张三"));
        Account account1 = new Account(1, 1, "100", new Demo("张三"));
        System.out.println(account.equals(account1));
    }
}

