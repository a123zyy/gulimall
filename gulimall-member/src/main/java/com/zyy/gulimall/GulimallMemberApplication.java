package com.zyy.gulimall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 要远程调用别的服务
 * 1.引入open-feign
 * 2.编写接口 @FeignClient("远程服务名")+具体服务地址
 * 3.@EnableFeignClients注解
 * 4.开启远程功能
 *
 * */
@EnableFeignClients(basePackages = "com.zyy.gulimall.member.feign")
@EnableDiscoveryClient
@SpringBootApplication
public class GulimallMemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(GulimallMemberApplication.class,args);
    }
}
