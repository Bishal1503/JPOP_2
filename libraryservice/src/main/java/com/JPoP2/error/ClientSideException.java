package com.JPoP2.error;

public class ClientSideException extends RuntimeException {
	private static final long serialVersionUID = 6074039594390901245L;

	public ClientSideException(String message) {
		super(message);
	}
}
