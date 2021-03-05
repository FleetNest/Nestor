package junit5.com.fleetnest.nestor.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

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
import static org.mockito.quality.Strictness.LENIENT;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import io.generators.core.Generator;

/**
 * @author Cihad Baskoy
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for the Data Service preparing the Sensor Data")
@MockitoSettings(strictness=LENIENT)
public class DataServiceTest {

	@Mock private Generator<SensorDetail> sensorDetailFactory;
	@Mock private Generator<SensorDetail> basicSensorDetailFactory;

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
		setField(service, "uniqueId", uniqueId);
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