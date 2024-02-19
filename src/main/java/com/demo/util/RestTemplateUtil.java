package com.demo.util;

import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestTemplateUtil {

	// Object to QueryParams
    public static MultiValueMap<String, String> convertDtoToQueryParams(Object dto) {
    	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    	
    	if(ObjectUtils.isEmpty(dto)) {
    		return params;
    	}
        
    	try {
    		ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> map = objectMapper.convertValue(dto, new TypeReference<Map<String, String>>() {});
            params.setAll(map);

            return params;
        } catch (Exception e) {
            throw e;
        }
    	
    }
    
    // Map to QueryParams
    public static MultiValueMap<String, String> convertMapToQueryParams(Map<String, String> map) {
    	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    	
    	if(ObjectUtils.isEmpty(map)) {
    		return params;
    	}
    	
    	params.setAll(map);
    	return params;
    	
    }
    
    // build URI
    public static String buildUri(String url, MultiValueMap<String, String> queryParams, Object ...pathParmas) {
		return UriComponentsBuilder.fromHttpUrl(url)
				.queryParams(queryParams)
				.build(pathParmas)
				.toString();
    }
    
	// return header
	public static HttpHeaders buildHeader(MediaType mediaType) {
		HttpHeaders headers = new HttpHeaders();
//		headers.setBearerAuth("token_123");
//		headers.set("ticket", "ticket_123");
		headers.setContentType(mediaType);
		
		return headers;
	}
	
}
