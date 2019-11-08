package com.JPoP2.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -6182296323456937252L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
