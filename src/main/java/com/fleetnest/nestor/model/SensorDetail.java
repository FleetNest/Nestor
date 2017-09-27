package com.fleetnest.nestor.model;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fleetnest.nestor.json.DateTimeSerializer;

/**
 * This is the main model for sensor data detail. 
 * All the sensor required fields are encapsulated in this class
 * 
 * @author Cihad Baskoy
 */
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
	
	private SensorDetail(LocalDateTime createDate, Boolean engineRunning, Integer speed, Double remainingFuel, Double fuelConsumption, Integer distance,
			Integer time, Double temperature, Integer humidity, Coordinate coordinate, Boolean emergency, Boolean crash) {

		this.createDate = createDate;
		this.engineRunning = engineRunning;
		this.speed = speed;
		this.remainingFuel = remainingFuel;
		this.fuelConsumption = fuelConsumption;
		this.distance = distance;
		this.time = time;
		this.temperature = temperature;
		this.humidity = humidity;
		this.coordinate = coordinate;
		this.emergency = emergency;
		this.crash = crash;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public Boolean isEngineRunning() {
		return engineRunning;
	}

	public Integer getSpeed() {
		return speed;
	}

	public Double getRemainingFuel() {
		return remainingFuel;
	}

	public Double getFuelConsumption() {
		return fuelConsumption;
	}

	public Integer getDistance() {
		return distance;
	}

	public Integer getTime() {
		return time;
	}

	public Double getTemperature() {
		return temperature;
	}

	public Integer getHumidity() {
		return humidity;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public Boolean isEmergency() {
		return emergency;
	}

	public Boolean isCrash() {
		return crash;
	}

	public static class Builder {

		private LocalDateTime createDate;
		private Boolean engineRunning;
		private Integer speed;
		private Double remainingFuel;
		private Double fuelConsumption;
		private Integer distance;
		private Integer time;
		private Double temperature;
		private Integer humidity;
		private Coordinate coordinate;
		private Boolean emergency;
		private Boolean crash;

		public Builder setCreateDate(LocalDateTime createDate) {
			this.createDate = createDate;
			return this;
		}

		public Builder setEngineRunning(Boolean engineRunning) {
			this.engineRunning = engineRunning;
			return this;
		}

		public Builder setSpeed(Integer speed) {
			this.speed = speed;
			return this;
		}

		public Builder setRemainingFuel(Double remainingFuel) {
			this.remainingFuel = remainingFuel;
			return this;
		}

		public Builder setFuelConsumption(Double fuelConsumption) {
			this.fuelConsumption = fuelConsumption;
			return this;
		}

		public Builder setDistance(Integer distance) {
			this.distance = distance;
			return this;
		}

		public Builder setTime(Integer time) {
			this.time = time;
			return this;
		}

		public Builder setTemperature(Double temperature) {
			this.temperature = temperature;
			return this;
		}

		public Builder setHumidity(Integer humidity) {
			this.humidity = humidity;
			return this;
		}

		public Builder setCoordinate(Coordinate coordinate) {
			this.coordinate = coordinate;
			return this;
		}

		public Builder setEmergency(Boolean emergencyButton) {
			this.emergency = emergencyButton;
			return this;
		}

		public Builder setCrash(Boolean crash) {
			this.crash = crash;
			return this;
		}
		
		public SensorDetail build() {
			return new SensorDetail(createDate, engineRunning, speed, remainingFuel, fuelConsumption, distance, time, temperature, humidity, coordinate, emergency, crash);
		}
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}