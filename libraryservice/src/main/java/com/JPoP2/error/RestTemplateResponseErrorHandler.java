package com.JPoP2.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

	@Override
	public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
		return (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR)
				|| (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
	}

	@Override
	public void handleError(ClientHttpResponse httpResponse) throws IOException {
		if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR)
			throw new ServerSideException("Server side error. Status code : " + httpResponse.getStatusCode());
		else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
			if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND)
				throw new ResourceNotFoundException("Resource Not Found");

			throw new ClientSideException("Client side error. Status code : " + httpResponse.getStatusCode());
		}
	}
}