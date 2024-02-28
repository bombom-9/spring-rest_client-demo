package com.demo.config;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {

		// before
		request.getHeaders().forEach((k,v) -> {
			log.info("[Request] " + k + " = " + v);
		});
		
		ClientHttpResponse response = execution.execute(request, body);
		
		// after
		response.getHeaders().forEach((k,v) -> {
			log.info("[Response] " + k + " = " + v);
		});
		
		return response;
	}

}
