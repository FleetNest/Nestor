package junit5.com.fleetnest.nestor.factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import com.fleetnest.nestor.factory.CoordinateFactory;
import com.fleetnest.nestor.model.Coordinate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;

import junit5.com.fleetnest.nestor.util.MockitoExtension;

/**
 * @author Cihad Baskoy
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
@DisplayName("Tests for the Coordinate Factory")
public class CoordinateFactoryTest {

	@InjectMocks
	private CoordinateFactory factory;

	@Test
	@DisplayName("Generate a Coordinate between ([41.00000-42.00000]-[29.00000-30.00000])")
	public void when_next_called_then_vaid_coordinate_generated() throws Exception {

		// When
		Coordinate actual = factory.next();

		// Then
		assertThat(actual.getLatitude(), is(greaterThan(41f)));
		assertThat(actual.getLatitude(), is(lessThan(42f)));
		assertThat(actual.getLongitude(), is(greaterThan(29f)));
		assertThat(actual.getLongitude(), is(lessThan(30f)));
	}
}