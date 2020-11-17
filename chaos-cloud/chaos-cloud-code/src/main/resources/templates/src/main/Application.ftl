package ${cfg.p};

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@MapperScan("com.${package.ModuleName}.service.mapper")
@ComponentScan(basePackages = {"com.firepongo","com.${package.ModuleName}"})
public class ${cfg.a}Application {

public static void main(String[] args) {
        SpringApplication.run(${cfg.a}Application.class, args);
    }
}
