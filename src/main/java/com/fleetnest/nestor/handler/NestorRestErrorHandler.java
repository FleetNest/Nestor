package com.fleetnest.nestor.handler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseErrorHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleetnest.nestor.exception.FleetnestRestClientException;
import com.fleetnest.nestor.model.RestResponse;

/**
 * If an error type object received through json response, this object will handle the data and throw internal exception
 * 
 * @author Cihad Baskoy
 */
@Service
public class NestorRestErrorHandler implements ResponseErrorHandler {

	@Autowired
	private ObjectMapper mapper;
	
	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		
		RestResponse restResponse = mapper.readValue(response.getBody(), RestResponse.class);
		response.close();
		
		throw new FleetnestRestClientException("Error from server with Json object " + restResponse, restResponse);
	}
	
	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return response.getStatusCode() != HttpStatus.OK && response.getStatusCode() != HttpStatus.FOUND;
	}
}