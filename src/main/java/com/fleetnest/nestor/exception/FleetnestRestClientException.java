package com.fleetnest.nestor.exception;

import org.springframework.web.client.RestClientException;

import com.fleetnest.nestor.model.RestResponse;

/**
 * Exception thrown if a RestResponse is error from the fleetnest application
 * 
 * @author Cihad Baskoy
 */
public class FleetnestRestClientException extends RestClientException {

	private RestResponse restResponse;
	
	public FleetnestRestClientException(String msg, RestResponse restResponse) {
		super(msg);
		this.restResponse = restResponse;
	}

	public RestResponse getRestResponse() {
		return restResponse;
	}
}
