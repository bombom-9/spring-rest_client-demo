package com.demo.config;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	@Bean
	public RestTemplate restTemplate() {
		
		RestTemplateBuilder builder = new RestTemplateBuilder();
		builder.setConnectTimeout(Duration.ofMillis(3000));
		builder.setReadTimeout(Duration.ofMillis(3000));

		RestTemplate restTemplate = builder.build();
		restTemplate.setErrorHandler(new RestTemplateErrorHandler());

		return restTemplate;

	}

}
