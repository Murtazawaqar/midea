package com.challenge.midea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages="com.challenge.midea.repository")
@EnableOAuth2Sso
public class MideaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MideaApplication.class, args);
	}

}
