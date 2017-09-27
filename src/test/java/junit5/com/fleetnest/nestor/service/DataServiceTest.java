package junit5.com.fleetnest.nestor.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.env.Environment;

import com.fleetnest.nestor.generator.SensorDetailGenerator;
import com.fleetnest.nestor.model.SensorData;
import com.fleetnest.nestor.model.SensorDetail;
import com.fleetnest.nestor.service.DataService;

import static io.generators.core.Generators.alphabetic;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import io.generators.core.Generator;
import junit5.com.fleetnest.nestor.util.MockitoExtension;

/**
 * @author Cihad Baskoy
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
@DisplayName("Tests for the Data Service preparing the Sensor Data")
public class DataServiceTest {

	@Mock
	private Environment env;
	
	@Mock
	private Generator<SensorDetail> sensorDetailFactory;

	@Mock
	private Generator<SensorDetail> basicSensorDetailFactory;

	@InjectMocks
	private DataService service;
	
	private String uniqueId;
	
	@BeforeEach
	public void init() throws Exception {
		
		// Generators
		Generator<SensorDetail> sensorDetailGenerator = new SensorDetailGenerator();
		
		// Default Fields
		uniqueId = alphabetic(16).next();

		// Mocks
		when(env.getProperty("device.data.uniqueId")).thenReturn(uniqueId);
		when(sensorDetailFactory.next()).thenReturn(sensorDetailGenerator.next());
		when(basicSensorDetailFactory.next()).thenReturn(sensorDetailGenerator.next());
	}
	
	@Test
	@DisplayName("Generate a valid single detail Sensor Data")
	public void when_single_data_request_then_valid_data_prepared() throws Exception {

		// When
		SensorData actual = service.singleDetailsData();

		// Then
		assertEquals(uniqueId, actual.getUniqueId());
		assertThat(actual.getSensors(), hasSize(equalTo(1)));
	}
	
	@Test
	@DisplayName("Generate a valid multiple detail Sensor Data")
	public void when_multiple_data_request_then_valid_data_prepared() throws Exception {
		
		// When
		SensorData actual = service.multipleDetailsData();

		// Then
		assertEquals(uniqueId, actual.getUniqueId());
		assertThat(actual.getSensors(), hasSize(greaterThan(1)));
	}
}