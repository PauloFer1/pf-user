package com.pfernand.pfuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableAutoConfiguration
@Configuration
public class PfUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(PfUserApplication.class, args);
	}
}
