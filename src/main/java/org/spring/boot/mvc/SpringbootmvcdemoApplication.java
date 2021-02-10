package org.spring.boot.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "org.spring.boot.mvc")
@EnableJpaRepositories(basePackages = "org.spring.boot.mvc.repo")
public class SpringbootmvcdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootmvcdemoApplication.class, args);
	}

}
