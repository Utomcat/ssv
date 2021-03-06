package com.ranyk.ssv;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ranyi
 */
@EnableSwagger2
@SpringBootApplication(scanBasePackages = {"com.ranyk.ssv"})
@MapperScan(basePackages = {"com.ranyk.ssv.**.dao"})
public class SsvApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsvApplication.class, args);
    }

}
