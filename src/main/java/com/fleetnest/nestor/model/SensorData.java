package com.fleetnest.nestor.model;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fleetnest.nestor.json.DateTimeSerializer;

/**
 * Main object that Fleetnest data collector accepts.
 * 
 * @author Cihad Baskoy
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SensorData {

	private String uniqueId;

	@JsonSerialize(using = DateTimeSerializer.class)
	private LocalDateTime createDate;

	private List<SensorDetail> sensors;
	
	private SensorData(String uniqueId, LocalDateTime createDate, List<SensorDetail> sensors) {
		this.uniqueId = uniqueId;
		this.createDate = createDate;
		this.sensors = sensors;
	}
	
	public String getUniqueId() {
		return uniqueId;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public List<SensorDetail> getSensors() {
		return Collections.unmodifiableList(sensors);
	}

	public static class Builder {

		private String uniqueId;
		private LocalDateTime createDate;
		private List<SensorDetail> sensors;
		
		public Builder setUniqueId(String uniqueId) {
			this.uniqueId = uniqueId;
			return this;
		}
		
		public Builder setCreateDate(LocalDateTime createDate) {
			this.createDate = createDate;
			return this;
		}
		
		public Builder setSensors(List<SensorDetail> sensors) {
			this.sensors = sensors;
			return this;
		}

		public SensorData build() {
			return new SensorData(uniqueId, createDate, sensors);
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}