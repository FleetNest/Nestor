package com.fleetnest.nestor.factory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.fleetnest.nestor.generator.CoordinateGenerator;
import com.fleetnest.nestor.model.Coordinate;
import com.fleetnest.nestor.model.SensorDetail;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import io.generators.core.Generator;

/**
 * @author Cihad Baskoy
 */
@RunWith(MockitoJUnitRunner.class)
public class SensorDetailFactoryTest {

	@Mock
	private CoordinateFactory coordinateFactory;
	
	@InjectMocks
	private SensorDetailFactory factory;
	
	private Generator<Coordinate> coordinateGenerator;

	@Before
	public void setUp() throws Exception {
		
		// Generators
		coordinateGenerator = new CoordinateGenerator();
	}
	
	@Test
	public void when_next_called_then_vaid_sensor_detail_generated() throws Exception {

		// Given
		when(coordinateFactory.next()).thenReturn(coordinateGenerator.next());
		
		// When
		SensorDetail actual = factory.next();

		// Then
		assertNotNull(actual.getCreateDate());
		assertNotNull(actual.getCoordinate());
		if(actual.getEngineRunning()) {
			assertThat(actual.getSpeed(), greaterThan(0));
			assertThat(actual.getSpeed(), lessThan(120));
			assertThat(actual.getFuelConsumption(), greaterThanOrEqualTo(0.1d));
			assertThat(actual.getFuelConsumption(), lessThan(0.7d));
			assertThat(actual.getDistance(), greaterThan(1));
			assertThat(actual.getDistance(), lessThan(1000));
			assertThat(actual.getTime(), greaterThan(1));
			assertThat(actual.getTime(), lessThan(1000));
		} else {
			assertThat(actual.getSpeed(), equalTo(0));
			assertThat(actual.getFuelConsumption(), equalTo(0d));
			assertThat(actual.getDistance(), equalTo(0));
			assertThat(actual.getTime(), equalTo(0));
		}

		assertThat(actual.getHumidity(), greaterThan(52));
		assertThat(actual.getHumidity(), lessThan(57));
		assertThat(actual.getTemperature(), greaterThan(18d));
		assertThat(actual.getTemperature(), lessThan(22d));
	}
}