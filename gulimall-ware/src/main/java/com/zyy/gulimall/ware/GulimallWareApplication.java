package com.zyy.gulimall.ware;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.zyy.gulimall.ware.feign")
public class GulimallWareApplication {
    public static void main(String[] args) {
        SpringApplication.run(GulimallWareApplication.class,args);
    }

}
