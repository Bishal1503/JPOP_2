package com.JPoP2.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -2449248282261666921L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
