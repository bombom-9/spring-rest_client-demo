package com.demo.util;

import java.util.List;

import org.springframework.web.client.RestClient.RequestHeadersSpec.ConvertibleClientHttpResponse;

import com.demo.server.exception.DfException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConvertUtil {

	public static <R> R HttpReponseToClass(Class<R> classType, ConvertibleClientHttpResponse response) throws Exception {
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		if(response.getStatusCode().is4xxClientError()) {
			DfException exception = objectMapper.readValue(response.getBody(), DfException.class);
			throw exception;
		}
		
		return objectMapper.readValue(response.getBody(), classType);
	}
	
    public static <R> List<R> HttpReponseToClassList(Class<R> classType, String rootName, ConvertibleClientHttpResponse response) throws Exception {
    	ObjectMapper objectMapper = new ObjectMapper();
    	
		if(response.getStatusCode().is4xxClientError()) {
			DfException exception = objectMapper.readValue(response.getBody(), DfException.class);
			throw exception;
		}
		
        return objectMapper.reader().forType(
                TypeFactory.defaultInstance().constructCollectionType(
                        List.class,
                        TypeFactory.defaultInstance().constructType(classType)))
                .withRootName(rootName)
                .readValue(response.getBody());
        
    }
    
}
