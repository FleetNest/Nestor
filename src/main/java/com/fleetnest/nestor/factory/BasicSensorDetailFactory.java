package com.fleetnest.nestor.factory;

import org.springframework.stereotype.Service;

import com.fleetnest.nestor.model.SensorDetail;

import static io.generators.core.Generators.positiveInts;
import static java.time.LocalDateTime.now;

import io.generators.core.Generator;

/**
 * This class generates a single basic (just isEngineRunning and speed field) sensor detail object
 * 
 * @author Cihad Baskoy
 */
@Service
public class BasicSensorDetailFactory implements Generator<SensorDetail> {

	@Override
	public SensorDetail next() {
		
		boolean isEngineRunning = positiveInts(1,101).next() > 5;
		int speed = isEngineRunning ? positiveInts(1,121).next() : 0;

		return new SensorDetail.Builder()
				.setEngineRunning(isEngineRunning)
				.setCreateDate(now())
				.setSpeed(speed)
				.build();
	}
}