package com.fleetnest.nestor.generator;

import com.fleetnest.nestor.model.Coordinate;
import com.fleetnest.nestor.model.SensorDetail;

import static io.generators.core.Generators.positiveInts;
import static java.time.LocalDateTime.now;

import io.generators.core.Generator;
import io.generators.core.RandomBooleanGenerator;

/**
 * This class generates a single sensor detail object
 * 
 * @author Cihad Baskoy
 */
public class SensorDetailGenerator implements Generator<SensorDetail> {

	private final Generator<Coordinate> coordinateGenerator;
	private final Generator<Boolean> booleanGenerator;

	public SensorDetailGenerator() {
		booleanGenerator = new RandomBooleanGenerator();
		coordinateGenerator = new CoordinateGenerator();
	}
	
	@Override
	public SensorDetail next() {

		return new SensorDetail.Builder()
				.setEngineRunning(booleanGenerator.next())
				.setCrash(booleanGenerator.next())
				.setEmergency(booleanGenerator.next())
				.setCreateDate(now())
				.setSpeed(positiveInts(1, 121).next())
				.setFuelConsumption(positiveInts(1, 7).next().doubleValue()/10)
				.setDistance(positiveInts(1, 1001).next())
				.setTime(positiveInts(1, 1001).next())
				.setCoordinate(coordinateGenerator.next())
				.setTemperature(18 + positiveInts(1, 40).next().doubleValue()/10)
				.setHumidity(52 + positiveInts(1, 5).next())
				.build();
	}
}