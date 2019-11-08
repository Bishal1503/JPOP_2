package com.JPoP2.LibraryService;

import com.JPoP2.error.RestTemplateResponseErrorHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
public class LibraryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryServiceApplication.class, args);
	}

	@LoadBalanced
	@Bean
	RestTemplate getRestTemplate(RestTemplateBuilder builder) {
		RestTemplate restTemplate = builder.build();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		return restTemplate;
	}

	@Bean
	InMemoryUserDetailsManager getInMemoryUserDetailsManager() {
		return new InMemoryUserDetailsManager();
	}

	@Bean
	PasswordEncoder getPasswordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
