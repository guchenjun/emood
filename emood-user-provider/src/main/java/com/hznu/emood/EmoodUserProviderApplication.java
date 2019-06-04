package com.hznu.emood;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@MapperScan("com.hznu.emood.mapper")
@SpringBootApplication
public class EmoodUserProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmoodUserProviderApplication.class, args);
    }

}
