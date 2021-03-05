package com.fleetnest.nestor.factory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.fleetnest.nestor.model.Coordinate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

/**
 * @author Cihad Baskoy
 */
@RunWith(MockitoJUnitRunner.class)
public class CoordinateFactoryTest {

	@InjectMocks
	private CoordinateFactory factory;

	@Test
	public void when_next_called_then_vaid_coordinate_generated() throws Exception {

		// When
		Coordinate actual = factory.next();

		// Then
		assertThat(actual.getLatitude(), greaterThan(41f));
		assertThat(actual.getLatitude(), lessThan(42f));
		assertThat(actual.getLongitude(), greaterThan(29f));
		assertThat(actual.getLongitude(), lessThan(30f));
	}
}