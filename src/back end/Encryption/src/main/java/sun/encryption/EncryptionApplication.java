package sun.encryption;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// 注解括号中的内容为取消
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class EncryptionApplication {

    public static void main(String[] args) {

        SpringApplication.run(EncryptionApplication.class, args);

    }

}
