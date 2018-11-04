package junit5.com.fleetnest.nestor.service;

import java.net.URI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import com.fleetnest.nestor.generator.SensorDataGenerator;
import com.fleetnest.nestor.generator.SensorDetailGenerator;
import com.fleetnest.nestor.handler.NestorRestErrorHandler.FleetnestRestClientException;
import com.fleetnest.nestor.model.RestResponse;
import com.fleetnest.nestor.model.SensorData;
import com.fleetnest.nestor.model.SensorDetail;
import com.fleetnest.nestor.service.RestClientService;

import static io.generators.core.Generators.alphabetic;
import static io.generators.core.Generators.positiveInts;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.quality.Strictness.LENIENT;

import io.generators.core.Generator;

/**
 * @author Cihad Baskoy
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for the Rest Client Endpoint")
@MockitoSettings(strictness=LENIENT)
public class RestClientServiceTest {

	@Mock
	private Environment env;

	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks
	private RestClientService service;
	
	private Generator<SensorData> sensorDataGenerator; 
	
	@BeforeEach
	public void init() throws Exception {
		
		// Generators
		Generator<SensorDetail> sensorDetailGenerator = new SensorDetailGenerator();
		sensorDataGenerator = new SensorDataGenerator(sensorDetailGenerator);
		
		when(env.getProperty("server.host")).thenReturn("localhost");
		when(env.getProperty("server.port", Integer.class)).thenReturn(positiveInts(1024, 9999).next());
		when(env.getProperty("device.data.path")).thenReturn("/rest/data");
	}

	@Test
	@DisplayName("Sends a sensor data over rest client successfully")
	public void given_sensor_data_when_send_data_called_then_successful_send_completed(@Mock RestResponse restResponse) throws Exception {

		// Given
		SensorData sensorData = sensorDataGenerator.next();
		when(restTemplate.postForObject(any(URI.class), eq(sensorData), eq(RestResponse.class))).thenReturn(restResponse);

		// When
		RestResponse actual = service.sendData(sensorData);

		// Then
		assertEquals(restResponse, actual);
	}
	
	@Test
	@DisplayName("Sends a sensor data over rest client and receives an error")
	public void given_sensor_data_when_send_data_with_error_called_then_exception_thrown(@Mock RestResponse restResponse) throws Exception {
		
		// Given
		SensorData sensorData = sensorDataGenerator.next();
		FleetnestRestClientException exc = new FleetnestRestClientException(alphabetic(256).next(), restResponse);
		when(restTemplate.postForObject(any(URI.class), eq(sensorData), eq(RestResponse.class))).thenThrow(exc);
		
		// When
		RestResponse actual = service.sendData(sensorData);
		
		// Then
		assertEquals(restResponse, actual);
	}
}