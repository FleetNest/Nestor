package com.fleetnest.nestor.service;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import com.fleetnest.nestor.generator.SensorDataGenerator;
import com.fleetnest.nestor.generator.SensorDetailGenerator;
import com.fleetnest.nestor.handler.NestorRestErrorHandler.FleetnestRestClientException;
import com.fleetnest.nestor.model.RestResponse;
import com.fleetnest.nestor.model.SensorData;
import com.fleetnest.nestor.model.SensorDetail;

import static io.generators.core.Generators.alphabetic;
import static io.generators.core.Generators.positiveInts;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import io.generators.core.Generator;

/**
 * @author Cihad Baskoy
 */
@RunWith(MockitoJUnitRunner.class)
public class RestClientServiceTest {

	@Mock
	private Environment env;

	@Mock
	private RestTemplate restTemplate;
	
	@Mock
	private RestResponse restResponse; 
	
	@InjectMocks
	private RestClientService service;
	
	private Generator<SensorData> sensorDataGenerator; 
	private Generator<SensorDetail> sensorDetailGenerator;
	
	@Before
	public void setUp() throws Exception {
		
		// Generators
		sensorDetailGenerator = new SensorDetailGenerator();
		sensorDataGenerator = new SensorDataGenerator(sensorDetailGenerator);
		
		when(env.getProperty("server.host")).thenReturn("localhost");
		when(env.getProperty("server.port", Integer.class)).thenReturn(positiveInts(1024, 9999).next());
		when(env.getProperty("device.data.path")).thenReturn("/rest/data");
	}

	@Test
	public void given_sensor_data_when_send_data_called_then_successful_send_completed() throws Exception {

		// Given
		SensorData sensorData = sensorDataGenerator.next();
		when(restTemplate.postForObject(any(URI.class), eq(sensorData), eq(RestResponse.class))).thenReturn(restResponse);

		// When
		RestResponse actual = service.sendData(sensorData);

		// Then
		assertEquals(restResponse, actual);
	}
	
	@Test
	public void given_sensor_data_when_send_data_with_error_called_then_exception_thrown() throws Exception {
		
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