package com.fleetnest.nestor.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

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

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(shape = STRING, pattern = "yyyy-MM-dd HH:mm:ss")
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