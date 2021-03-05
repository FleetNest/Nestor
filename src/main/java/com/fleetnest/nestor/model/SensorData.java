package com.fleetnest.nestor.model;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Main object that Fleetnest data collector accepts.
 * 
 * @author Cihad Baskoy
 */
@Builder
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SensorData {

	private final String uniqueId;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(shape = STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private final LocalDateTime createDate;

	private final List<SensorDetail> sensors;
	
	public List<SensorDetail> getSensors() {
		return Collections.unmodifiableList(sensors);
	}
}