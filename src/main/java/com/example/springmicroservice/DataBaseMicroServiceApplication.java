package com.example.springmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan("com")
@EnableJpaRepositories("com.policy.repo")
@EntityScan("com.entity")
@EnableEurekaClient
@EnableSwagger2
public class DataBaseMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataBaseMicroServiceApplication.class, args);
	}

}
