package com.fleetnest.nestor.factory;

import org.springframework.stereotype.Service;

import com.fleetnest.nestor.model.Coordinate;
import com.fleetnest.nestor.model.SensorDetail;

import static io.generators.core.Generators.positiveInts;
import static java.time.LocalDateTime.now;

import io.generators.core.Generator;
import lombok.RequiredArgsConstructor;

/**
 * This class generates a single sensor detail object
 * 
 * @author Cihad Baskoy
 */
@Service
@RequiredArgsConstructor
public class SensorDetailFactory implements Generator<SensorDetail> {

	private final Generator<Coordinate> coordinateFactory;
	
	@Override
	public SensorDetail next() {
		
		boolean isEngineRunning = positiveInts(1,101).next() > 5;
		int speed = isEngineRunning ? positiveInts(1,121).next() : 0;
		boolean isCrash = positiveInts(1,1000).next() > 5;
		boolean isEmergency = positiveInts(1,1000).next() > 5;
		double temperature = 18 + positiveInts(1, 40).next().doubleValue()/10;
		int humidity = 52 + positiveInts(1, 5).next();
		int time = isEngineRunning ? positiveInts(1, 1001).next() : 0;
		int distance = isEngineRunning ? positiveInts(1, 1001).next() : 0;
		double fuelConsumption = isEngineRunning ? positiveInts(1, 7).next().doubleValue()/10 : 0;

		return SensorDetail.builder()
				.engineRunning(isEngineRunning)
				.crash(isCrash)
				.emergency(isEmergency)
				.createDate(now())
				.speed(speed)
				.fuelConsumption(fuelConsumption)
				.distance(distance)
				.time(time)
				.coordinate(coordinateFactory.next())
				.temperature(temperature)
				.humidity(humidity)
				.build();
	}
}