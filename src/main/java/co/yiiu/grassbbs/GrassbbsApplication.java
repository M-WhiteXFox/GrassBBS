package co.yiiu.grassbbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// 不用默认配置的数据源，自己配置
@SpringBootApplication(scanBasePackages = "co.yiiu.grassbbs",
        exclude = {DataSourceAutoConfiguration.class, FlywayAutoConfiguration.class})
@EnableAspectJAutoProxy
public class GrassbbsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GrassbbsApplication.class, args);
    }
}
