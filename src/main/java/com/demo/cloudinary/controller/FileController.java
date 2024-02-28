package com.demo.cloudinary.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.demo.cloudinary.exception.CloudinaryException;
import com.demo.cloudinary.service.FileService;
import com.demo.cloudinary.vo.FileListSearchResponse;
import com.demo.cloudinary.vo.FileUploadResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FileController {
	
	private final FileService fileService;
	
	@GetMapping("/files")
	public FileListSearchResponse getFiles() {
		
		log.info("Get /files Requested : ");
		
		ResponseEntity<FileListSearchResponse> response;
		
		try {
			response = fileService.getFiles();
		} catch (CloudinaryException e) {
			throw new ResponseStatusException(e.getHttpStatusCode(), e.getError().getMessage());
		}
		
		return response.getBody();
		
	}

	@GetMapping("/files/{id}")
	public FileListSearchResponse getFile(@PathVariable String id) {
		
		ResponseEntity<FileListSearchResponse> response;
		
		try {
			response = fileService.getFiles();
		} catch (CloudinaryException e) {
			throw new ResponseStatusException(e.getHttpStatusCode(), e.getError().getMessage());
		}
		
		return response.getBody();
		
	}
	
	@PostMapping("/files")
	public FileUploadResponse uploadFiles(MultipartFile[] files) {
		
		ResponseEntity<FileUploadResponse> response;
		
		try {
			response = fileService.uploadFiles(files);
		} catch (CloudinaryException e) {
			throw new ResponseStatusException(e.getHttpStatusCode(), e.getError().getMessage());
		}
		
		return response.getBody();
		
	}

	@GetMapping("/file/{id}/download")
	public void downloadFiles(@PathVariable String id) {
		
	}

}
