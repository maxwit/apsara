package com.zzj.firstspringcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FirstspringcloudServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(FirstspringcloudServerApplication.class, args);
	}
}
