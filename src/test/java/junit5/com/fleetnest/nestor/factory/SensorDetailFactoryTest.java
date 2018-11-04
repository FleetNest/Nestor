package junit5.com.fleetnest.nestor.factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fleetnest.nestor.factory.CoordinateFactory;
import com.fleetnest.nestor.factory.SensorDetailFactory;
import com.fleetnest.nestor.generator.CoordinateGenerator;
import com.fleetnest.nestor.model.Coordinate;
import com.fleetnest.nestor.model.SensorDetail;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumingThat;
import static org.mockito.Mockito.when;

import io.generators.core.Generator;

/**
 * @author Cihad Baskoy
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for the Sensor Factory")
public class SensorDetailFactoryTest {

	@Mock
	private CoordinateFactory coordinateFactory;
	
	@InjectMocks
	private SensorDetailFactory factory;

	@Test
	@DisplayName("Generate valid Sensor Detail")
	public void when_next_called_then_valid_sensor_detail_generated() throws Exception {

		// Given
		Generator<Coordinate> coordinateGenerator = new CoordinateGenerator();
		when(coordinateFactory.next()).thenReturn(coordinateGenerator.next());
		
		// When
		SensorDetail actual = factory.next();

		// Then
		assertNotNull(actual.getCreateDate());
		assertNotNull(actual.getCoordinate());
		
		assumingThat(actual.getEngineRunning(), () -> {
			assertThat(actual.getSpeed(), is(greaterThan(0)));
			assertThat(actual.getSpeed(), is(lessThan(120)));
			assertThat(actual.getFuelConsumption().doubleValue(), is(greaterThanOrEqualTo(0.1d)));
			assertThat(actual.getFuelConsumption().doubleValue(), is(lessThan(0.7d)));
			assertThat(actual.getDistance(), is(greaterThanOrEqualTo(1)));
			assertThat(actual.getDistance(), is(lessThan(1000)));
			assertThat(actual.getTime(), is(greaterThanOrEqualTo(1)));
			assertThat(actual.getTime(), is(lessThan(1000)));
		});
		
		assumingThat(!actual.getEngineRunning(), () -> {
			assertThat(actual.getSpeed(), is(equalTo(0)));
			assertThat(actual.getFuelConsumption().doubleValue(), is(equalTo(0d)));
			assertThat(actual.getDistance(), is(equalTo(0)));
			assertThat(actual.getTime(), is(equalTo(0)));
		});
		
		assertThat(actual.getHumidity(), is(greaterThan(52)));
		assertThat(actual.getHumidity(), is(lessThan(57)));
		assertThat(actual.getTemperature(), is(greaterThan(18d)));
		assertThat(actual.getTemperature(), is(lessThan(22d)));
	}
}