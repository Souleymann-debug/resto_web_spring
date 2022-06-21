package com.restoto.restoto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"com.restoto.*"})
@EntityScan("com.restoto.model")
@EnableJpaRepositories("com.restoto.repository")
@ComponentScan(basePackages = "com.restoto.*")
public class RestotoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestotoApplication.class, args);
		
	}


}
