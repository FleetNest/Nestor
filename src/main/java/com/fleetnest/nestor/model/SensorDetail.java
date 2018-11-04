package com.fleetnest.nestor.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fleetnest.nestor.json.DateTimeSerializer;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * This is the main model for sensor data detail. 
 * All the sensor required fields are encapsulated in this class
 * 
 * @author Cihad Baskoy
 */
@Builder
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SensorDetail {

	@JsonSerialize(using = DateTimeSerializer.class)
	private LocalDateTime createDate;

	private final Boolean engineRunning;
	private final Integer speed;
	private final Double remainingFuel;
	private final Double fuelConsumption;
	private final Integer distance;
	private final Integer time;
	private final Double temperature;
	private final Integer humidity;
	private final Coordinate coordinate;
	private final Boolean emergency;
	private final Boolean crash;
}