package junit5.com.fleetnest.nestor.factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fleetnest.nestor.factory.BasicSensorDetailFactory;
import com.fleetnest.nestor.model.SensorDetail;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumingThat;

/**
 * @author Cihad Baskoy
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for the Basic Sensor Factory")
public class BasicSensorDetailFactoryTest {

	@InjectMocks
	private BasicSensorDetailFactory factory;

	@Test
	@DisplayName("Generate valid Basic (limited field) Sensor Detail")
	public void when_next_called_then_valid_basic_sensor_detail_generated() throws Exception {

		// When
		SensorDetail actual = factory.next();

		// Then
		assumingThat(actual.getEngineRunning(), () -> {
			assertThat(actual.getSpeed(), is(greaterThan(0)));
			assertThat(actual.getSpeed(), is(lessThan(120)));
		});
		
		assumingThat(!actual.getEngineRunning(), () -> {
			assertThat(actual.getSpeed(), is(equalTo(0)));
		});
		
		assertNotNull(actual.getCreateDate());
	}
}