package com.fleetnest.nestor.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import com.fleetnest.nestor.generator.SensorDetailGenerator;
import com.fleetnest.nestor.model.SensorData;
import com.fleetnest.nestor.model.SensorDetail;

import static io.generators.core.Generators.alphabetic;
import static java.time.LocalDateTime.now;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import io.generators.core.Generator;

/**
 * @author Cihad Baskoy
 */
@RunWith(MockitoJUnitRunner.class)
public class DataServiceTest {

	@Mock
	private Environment env;
	
	@Mock
	private Generator<SensorDetail> sensorDetailFactory;

	@Mock
	private Generator<SensorDetail> basicSensorDetailFactory;

	@InjectMocks
	private DataService service;
	
	private Generator<SensorDetail> sensorDetailGenerator;

	private String uniqueId;
	
	@Before
	public void setUp() throws Exception {
		
		// Generators
		sensorDetailGenerator = new SensorDetailGenerator();
		
		// Default Fields
		uniqueId = alphabetic(16).next();

		// Mocks
		when(env.getProperty("device.data.uniqueId")).thenReturn(uniqueId);
		when(sensorDetailFactory.next()).thenReturn(sensorDetailGenerator.next());
		when(basicSensorDetailFactory.next()).thenReturn(sensorDetailGenerator.next());
	}
	
	@Test
	public void when_single_data_request_then_valid_data_prepared() throws Exception {

		// When
		SensorData actual = service.singleDetailsData();

		// Then
		assertEquals(uniqueId, actual.getUniqueId());
		assertThat(actual.getSensors(), hasSize(equalTo(1)));
		assertTrue(actual.getCreateDate().isBefore(now().plusSeconds(1)));
	}
	
	@Test
	public void when_multiple_data_request_then_valid_data_prepared() throws Exception {
		
		// When
		SensorData actual = service.multipleDetailsData();
		
		// Then
		assertEquals(uniqueId, actual.getUniqueId());
		assertThat(actual.getSensors(), hasSize(greaterThan(1)));
		assertTrue(actual.getCreateDate().isBefore(now().plusSeconds(1)));
	}
}