package com.zzj.firstspringcloudclient2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class Firstspringcloudclient2Application {
    public static void main(String[] args) {
        SpringApplication.run(Firstspringcloudclient2Application.class, args);
    }

    @Value("${server.port}")
    String port;

    @RequestMapping(value = "/hi")
    public String home(@RequestParam String name) {
        return "hi "+name+",i am from port:" +port;	}
}