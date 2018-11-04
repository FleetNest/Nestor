package com.fleetnest.nestor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.fleetnest.nestor.model.SensorData;
import com.fleetnest.nestor.model.SensorDetail;

import static io.generators.core.Generators.positiveInts;
import static java.time.LocalDateTime.now;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Stream.generate;

import io.generators.core.Generator;

/**
 * Data Service that generates single or multiple random data
 * 
 * @author Cihad Baskoy
 */
@Service
public class DataService {

	@Autowired
	private Environment env;
	
	@Autowired
	private Generator<SensorDetail> sensorDetailFactory;

	@Autowired
	private Generator<SensorDetail> basicSensorDetailFactory;

	public SensorData singleDetailsData() {
		return SensorData.builder()
				.uniqueId(env.getProperty("device.data.uniqueId"))
				.createDate(now())
				.sensors(asList(sensorDetailFactory.next()))
				.build();
	}

	public SensorData multipleDetailsData() {
		
		return SensorData.builder()
				.uniqueId(env.getProperty("device.data.uniqueId"))
				.createDate(now())
				.sensors(sensors())
				.build();
	}

	private List<SensorDetail> sensors() {
		
		Integer sensorDetailSize = positiveInts(1, 300).next();

		List<SensorDetail> sensors = new ArrayList<>(sensorDetailSize + 1);
		sensors.add(sensorDetailFactory.next());
		
		return generate(basicSensorDetailFactory::next).limit(sensorDetailSize).collect(toCollection(() -> sensors));
	}
}