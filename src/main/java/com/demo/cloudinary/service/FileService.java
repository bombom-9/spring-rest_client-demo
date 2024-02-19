package com.demo.cloudinary.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.demo.cloudinary.vo.FileListSearchResponse;
import com.demo.cloudinary.vo.FileUploadResponse;
import com.demo.util.RestTemplateUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {

	private final RestTemplate restTemplate;
	
	private final static String API_KEY = "356845491768893";
	private final static String API_SECRET = "lOJ_p4LSgD-nKdOTCjCSa7Xy7nE";
	
	private final static String CLOUD_NAME = "dh5rzcghk";
	private final static String RESOURECE_TYPE = "image";
	private final static String ACTION_UPLOAD = "upload";
	
	private final static String UPLOAD_PRESET = "hfpyi9bg";
	
	public ResponseEntity<FileListSearchResponse> getFiles() {
		
//		Map<String, String> queryParams = new HashMap<String, String>();
//		queryParams.put("key1", "api_key_1");
//		queryParams.put("key2", "api_key_2");
//		queryParams.put("key3", "api_key_3");
//
//		MultiValueMap<String, String> params = RestTemplateUtil.convertMapToQueryParams(queryParams);
		
		// URI
		String uri = RestTemplateUtil.buildUri(
				"https://{api_key}:{api_secret}@api.cloudinary.com/v1_1/{cloud_name}/resources/{resource_type}",
				null, // params
				API_KEY, API_SECRET, CLOUD_NAME, RESOURECE_TYPE);
		
		// Header
		HttpHeaders headers = RestTemplateUtil.buildHeader(MediaType.APPLICATION_JSON);
		
		// Request
		ResponseEntity<FileListSearchResponse> response = restTemplate.exchange(
				uri,
				HttpMethod.GET,
				new HttpEntity<>(null, headers),
				new ParameterizedTypeReference<FileListSearchResponse>() {});
		
		return response;
		
	}
	
	public void getFile() {
		
	}
	
	public ResponseEntity<FileUploadResponse> uploadFiles(MultipartFile[] files) {
		
		// URI
		String uri = RestTemplateUtil.buildUri(
				"https://api.cloudinary.com/v1_1/{cloud_name}/{resource_type}/{action}",
				null,
				CLOUD_NAME, RESOURECE_TYPE, ACTION_UPLOAD);
		
		// Header
		HttpHeaders headers = RestTemplateUtil.buildHeader(MediaType.MULTIPART_FORM_DATA);
		
		// Body
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("upload_preset", UPLOAD_PRESET);
		for (MultipartFile file : files) {
			body.add("file", file.getResource());
		}
		
		// Request
		ResponseEntity<FileUploadResponse> response = restTemplate.exchange(
				uri,
				HttpMethod.POST,
				new HttpEntity<>(body, headers),
				new ParameterizedTypeReference<FileUploadResponse>() {});
		
		return response;
		
	}
	
	public void downloadFile() {
		
	}
	
}
