package com.zyy.gulimall.produt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ProuctApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProuctApplication.class, args);
    }

}
