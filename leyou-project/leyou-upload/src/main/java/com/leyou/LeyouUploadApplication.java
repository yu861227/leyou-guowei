package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author guo
 */
@SpringBootApplication
@EnableDiscoveryClient
//@EnableEurekaClient 这里是错误的
public class LeyouUploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeyouUploadApplication.class, args);
    }
}
