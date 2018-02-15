package com.koko.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages="com.koko.spring.*")
public class AppMain {

	public static void main(String[] args) {
		SpringApplication.run(AppMain.class, args);
	}
}
