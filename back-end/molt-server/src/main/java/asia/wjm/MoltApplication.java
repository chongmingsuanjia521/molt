package asia.wjm;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("asia.wjm.mapper")
public class MoltApplication {
    public static void main(String[] args) {
        SpringApplication.run(MoltApplication.class, args);
    }

}
