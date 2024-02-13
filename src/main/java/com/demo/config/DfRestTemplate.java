package com.demo.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class DfRestTemplate {

	private RestTemplate restTemplate;

	public DfRestTemplate() {

		RestTemplateBuilder builder = new RestTemplateBuilder();
		builder.setConnectTimeout(Duration.ofMillis(3000));
		builder.setReadTimeout(Duration.ofMillis(3000));

		RestTemplate restTemplate = builder.build();
		restTemplate.setErrorHandler(new RestTemplateErrorHandler());

		this.restTemplate = restTemplate;

	}

	public <R> R exchange(HttpMethod httpMethod, HttpHeaders headers, String url,
			MultiValueMap<String, String> queryParams, Map<String, String> uriVariables, Class<R> classType) {

		HttpEntity<HttpHeaders> httpEntity = new HttpEntity<HttpHeaders>(headers);

		String uri = UriComponentsBuilder.fromHttpUrl(url).queryParams(queryParams)
				.build(Optional.ofNullable(uriVariables).orElseGet(() -> new HashMap<>())).toString();

		ResponseEntity<R> response = restTemplate.exchange(
				uri,
				HttpMethod.GET,
				httpEntity,
				ParameterizedTypeReference.forType(classType));
		
		return response.getBody();

	}

}
