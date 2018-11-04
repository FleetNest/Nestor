package com.fleetnest.nestor.factory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.fleetnest.nestor.model.SensorDetail;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author Cihad Baskoy
 */
@RunWith(MockitoJUnitRunner.class)
public class BasicSensorDetailFactoryTest {

	@InjectMocks
	private BasicSensorDetailFactory factory;

	@Test
	public void when_next_called_then_vaid_basic_sensor_detail_generated() throws Exception {

		// When
		SensorDetail actual = factory.next();

		// Then
		assertNotNull(actual.getCreateDate());
		if(actual.getEngineRunning()) {
			assertThat(actual.getSpeed(), greaterThan(0));
			assertThat(actual.getSpeed(), lessThan(120));
		} else {
			assertThat(actual.getSpeed(), equalTo(0));
		}
	}
}