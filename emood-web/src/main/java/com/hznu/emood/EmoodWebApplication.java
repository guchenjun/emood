package com.hznu.emood;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableDubbo
@SpringBootApplication
public class EmoodWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmoodWebApplication.class, args);
    }

}
