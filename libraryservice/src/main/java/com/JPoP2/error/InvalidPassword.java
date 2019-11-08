package com.JPoP2.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidPassword extends RuntimeException {
	private static final long serialVersionUID = 4977638124309383931L;

	public InvalidPassword(String message) {
		super(message);
	}
}
