package com.demo.config;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.server.ResponseStatusException;

import com.demo.server.exception.DfException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RestTemplateErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return httpResponse.getStatusCode().is5xxServerError() || 
            httpResponse.getStatusCode().is4xxClientError();
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
    	ObjectMapper objectMapper = new ObjectMapper();
    	objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    	
        if (httpResponse.getStatusCode().is5xxServerError()) {
            throw new ResponseStatusException(httpResponse.getStatusCode());
            
        } else if (httpResponse.getStatusCode().is4xxClientError()) {
        	DfException exception = objectMapper.readValue(httpResponse.getBody(), DfException.class);
			throw exception;
        }
    }
    
}
