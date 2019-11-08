package com.JPoP2.error;

public class ServerSideException extends RuntimeException {
	private static final long serialVersionUID = 4089276060118213418L;

	public ServerSideException(String message) {
		super(message);
	}
}
