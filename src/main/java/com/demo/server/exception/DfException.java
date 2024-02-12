package com.demo.server.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DfException extends RuntimeException{
	
	private static final long serialVersionUID = -6098915803633860699L;
	
	private DfError error;
	
	@Getter
	@Setter
	public static class DfError {
		
		private int status;
		
		private String code;
		
		private String message;
		
	}
	
}
