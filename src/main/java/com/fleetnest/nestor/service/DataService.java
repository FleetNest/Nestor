package com.fleetnest.nestor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.fleetnest.nestor.model.SensorData;
import com.fleetnest.nestor.model.SensorDetail;

import static java.time.LocalDateTime.now;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Stream.generate;

import io.generators.core.Generator;
import io.generators.core.Generators;

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
		return new SensorData.Builder()
				.setUniqueId(env.getProperty("device.data.uniqueId"))
				.setCreateDate(now())
				.setSensors(asList(sensorDetailFactory.next()))
				.build();
	}

	public SensorData multipleDetailsData() {
		
		return new SensorData.Builder()
				.setUniqueId(env.getProperty("device.data.uniqueId"))
				.setCreateDate(now())
				.setSensors(sensors())
				.build();
	}

	private List<SensorDetail> sensors() {
		
		Integer sensorDetailSize = Generators.positiveInts(1, 300).next();

		List<SensorDetail> sensors = new ArrayList<>(sensorDetailSize + 1);
		sensors.add(sensorDetailFactory.next());
		
		return generate(basicSensorDetailFactory::next).limit(sensorDetailSize).collect(toCollection(() -> sensors));
	}
}