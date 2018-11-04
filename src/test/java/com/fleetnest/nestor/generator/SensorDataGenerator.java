package com.fleetnest.nestor.generator;

import java.util.List;

import com.fleetnest.nestor.model.SensorData;
import com.fleetnest.nestor.model.SensorDetail;

import static io.generators.core.Generators.alphabetic;
import static io.generators.core.Generators.positiveInts;
import static java.time.LocalDateTime.now;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.generate;

import io.generators.core.Generator;

/**
 * This is the main generator class for sensor data
 * 
 * @author Cihad Baskoy
 */
public class SensorDataGenerator implements Generator<SensorData> {

	private final Generator<SensorDetail> sensorDetailGenerator;

	public SensorDataGenerator(Generator<SensorDetail> sensorDetailGenerator) {
		this.sensorDetailGenerator = sensorDetailGenerator;
	}
	
	@Override
	public SensorData next() {
		
		int uniqueIdLength = positiveInts(1, 17).next();
		int sensorDetailSize = positiveInts(1, 101).next();
		String uniqueId = alphabetic(uniqueIdLength).next();

		List<SensorDetail> sensors = generate(sensorDetailGenerator::next)
				.limit(sensorDetailSize)
				.collect(toList());
		
		return SensorData.builder()
				.createDate(now())
				.uniqueId(uniqueId)
				.sensors(sensors)
				.build();
	}
}