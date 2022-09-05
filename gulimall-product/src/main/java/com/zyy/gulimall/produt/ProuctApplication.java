package com.zyy.gulimall.produt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableDiscoveryClient 服务注册到注册中心
@EnableFeignClients(basePackages = "com.zyy.gulimall.produt.feign")
@EnableDiscoveryClient
@SpringBootApplication
@EnableAsync
//@EnableTransactionManagement 的作用?
public class ProuctApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProuctApplication.class, args);
    }

}
