package com.example.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: csl
 * @description: vod启动类
 * @date: 2020-10-07 01:12
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) //配置文件中不用配置数据库
@ComponentScan(basePackages = {"com.example"})
@EnableDiscoveryClient
public class VodApplication {
    public static void main(String[] args) {
        SpringApplication.run(VodApplication.class, args);
    }
}