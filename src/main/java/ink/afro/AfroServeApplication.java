package ink.afro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan({"ink.afro.mapper"})
@SpringBootApplication
@EnableTransactionManagement
public class AfroServeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AfroServeApplication.class, args);
    }

}
