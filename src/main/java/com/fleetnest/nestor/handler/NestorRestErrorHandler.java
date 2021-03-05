package com.fleetnest.nestor.handler;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleetnest.nestor.model.RestResponse;

import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.OK;

import lombok.RequiredArgsConstructor;

/**
 * If an error type object received through json response, this object will handle the data and throw internal exception
 * 
 * @author Cihad Baskoy
 */
@Service
@RequiredArgsConstructor
public class NestorRestErrorHandler implements ResponseErrorHandler {

	private final ObjectMapper mapper;
	
	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		
		RestResponse restResponse = mapper.readValue(response.getBody(), RestResponse.class);
		response.close();
		
		throw new FleetnestRestClientException("Error from server with Json object " + restResponse, restResponse);
	}
	
	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return response.getStatusCode() != OK && response.getStatusCode() != FOUND;
	}

	public static class FleetnestRestClientException extends RestClientException {

		private RestResponse restResponse;
		
		public FleetnestRestClientException(String msg, RestResponse restResponse) {
			super(msg);
			this.restResponse = restResponse;
		}

		public RestResponse getRestResponse() {
			return restResponse;
		}
	}
}