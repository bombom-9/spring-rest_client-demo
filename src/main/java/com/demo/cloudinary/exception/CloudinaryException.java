package com.demo.cloudinary.exception;

import org.springframework.http.HttpStatusCode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CloudinaryException extends RuntimeException {

	private static final long serialVersionUID = 8269806962592399213L;
	
	private CloudinaryError error;
	
	private HttpStatusCode httpStatusCode;
	
	@Getter
	@Setter
	public static class CloudinaryError {
		private String message;
	}
	
}
