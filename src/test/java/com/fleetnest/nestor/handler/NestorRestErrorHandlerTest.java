package com.fleetnest.nestor.handler;

import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleetnest.nestor.generator.HttpStatusGenerator;
import com.fleetnest.nestor.handler.NestorRestErrorHandler.FleetnestRestClientException;
import com.fleetnest.nestor.model.RestResponse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.OK;

import io.generators.core.Generator;

/**
 * @author Cihad Baskoy
 */
@RunWith(MockitoJUnitRunner.class)
public class NestorRestErrorHandlerTest {

	@Mock
	private ObjectMapper mapper;
	
	@Mock
	private ClientHttpResponse response;

	// Class under test
	@InjectMocks
	private NestorRestErrorHandler handler;
	
	private Generator<HttpStatus> httpStatusGenerator;
	
	@Before
	public void init() throws Exception {
		
		// Generators
		httpStatusGenerator = new HttpStatusGenerator(OK, FOUND);
	}
	
	@Test
	public void given_response_when_handle_error_called_then_correct_exception_thrown() throws Exception {

		// Given
		InputStream is = mock(InputStream.class);
		RestResponse restResponse = mock(RestResponse.class);
		when(response.getBody()).thenReturn(is);
		when(mapper.readValue(is, RestResponse.class)).thenReturn(restResponse);

		// When
		FleetnestRestClientException actual = null;
		try {
			handler.handleError(response);
		} catch (FleetnestRestClientException ex) {
			actual = ex;
		}

		// Then
		verify(response, times(1)).close();
		assertThat(actual.getRestResponse(), is(restResponse));
	}
	
	@Test
	public void given_status_not_ok_or_found_when_has_errors_called_then_returns_true() throws Exception {
		
		// Given
		when(response.getStatusCode()).thenReturn(httpStatusGenerator.next());
		
		// When
		boolean actual = handler.hasError(response);
		
		// Then
		assertTrue(actual);
	}
	
	@Test
	public void given_status_found_when_has_errors_called_then_returns_false() throws Exception {
		
		// Given
		when(response.getStatusCode()).thenReturn(FOUND);
		
		// When
		boolean actual = handler.hasError(response);
		
		// Then
		assertFalse(actual);
	}
	
	@Test
	public void given_status_ok_when_has_errors_called_then_returns_false() throws Exception {
		
		// Given
		when(response.getStatusCode()).thenReturn(OK);
		
		// When
		boolean actual = handler.hasError(response);
		
		// Then
		assertFalse(actual);
	}
}