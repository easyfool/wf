package com.wf.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.wf.cloud.feign"})
public class Order9001 {
    public static void main(String[] args) {
        SpringApplication.run(Order9001.class);
    }
}
