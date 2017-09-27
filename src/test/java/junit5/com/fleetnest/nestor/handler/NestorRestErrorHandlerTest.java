package junit5.com.fleetnest.nestor.handler;

import java.io.InputStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleetnest.nestor.exception.FleetnestRestClientException;
import com.fleetnest.nestor.generator.HttpStatusGenerator;
import com.fleetnest.nestor.handler.NestorRestErrorHandler;
import com.fleetnest.nestor.model.RestResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.OK;

import io.generators.core.Generator;
import junit5.com.fleetnest.nestor.util.MockitoExtension;

/**
 * @author Cihad Baskoy
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
@DisplayName("Tests for the Error Handler for Rest Responses from FleetNest")
public class NestorRestErrorHandlerTest {

	@Mock
	private ObjectMapper mapper;
	
	@InjectMocks
	private NestorRestErrorHandler handler;
	
	@Test
	@DisplayName("Expected exception thrown when received an error from FleetNest")
	public void given_response_when_handle_error_called_then_correct_exception_thrown(@Mock ClientHttpResponse response) throws Exception {

		// Given
		InputStream is = mock(InputStream.class);
		RestResponse restResponse = mock(RestResponse.class);
		when(response.getBody()).thenReturn(is);
		when(mapper.readValue(is, RestResponse.class)).thenReturn(restResponse);

		// When
		FleetnestRestClientException actual = assertThrows(FleetnestRestClientException.class, () -> handler.handleError(response));

		// Then
		verify(response, times(1)).close();
		assertThat(actual.getRestResponse(), is(restResponse));
	}
	
	@Test
	@DisplayName("Returns hasError TRUE when the status is not OK or FOUND (200 & 302)")
	public void given_status_not_ok_or_found_when_has_errors_called_then_returns_true(@Mock ClientHttpResponse response) throws Exception {
		
		// Given
		Generator<HttpStatus> httpStatusGenerator = new HttpStatusGenerator(OK, FOUND);
		when(response.getStatusCode()).thenReturn(httpStatusGenerator.next());
		
		// When
		boolean actual = handler.hasError(response);
		
		// Then
		assertTrue(actual);
	}
	
	@Test
	@DisplayName("Returns hasError FALSE when the status is FOUND (302)")
	public void given_status_found_when_has_errors_called_then_returns_false(@Mock ClientHttpResponse response) throws Exception {
		
		// Given
		when(response.getStatusCode()).thenReturn(FOUND);
		
		// When
		boolean actual = handler.hasError(response);
		
		// Then
		assertFalse(actual);
	}
	
	@Test
	@DisplayName("Returns hasError FALSE when the status is OK (200)")
	public void given_status_ok_when_has_errors_called_then_returns_false(@Mock ClientHttpResponse response) throws Exception {
		
		// Given
		when(response.getStatusCode()).thenReturn(OK);
		
		// When
		boolean actual = handler.hasError(response);
		
		// Then
		assertFalse(actual);
	}
}