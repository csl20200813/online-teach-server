package com.example.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: csl
 * @description: cms的启动类
 * @date: 2020-10-08 20:21
 */

@SpringBootApplication
@ComponentScan("com.example")
@MapperScan("com.example.educms.mapper")
@EnableDiscoveryClient  //nacos注册
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}