package com.fleetnest.nestor.model;

import java.text.DecimalFormat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * A class to represent a latitude and longitude
 * 
 * @author Cihad Baskoy
 */
@Getter
@ToString
@EqualsAndHashCode
public class Coordinate {

	private final Float latitude;
	private final Float longitude;

	@JsonIgnore
	private DecimalFormat format;

	/**
	 * Constructor for this class
	 *
	 * @param latitude a latitude coordinate in decimal notation
	 * @param longitude a longitude coordinate in decimal notation
	 */
	@JsonCreator
	public Coordinate(@JsonProperty("latitude") Float latitude, @JsonProperty("longitude") Float longitude) {

		this.latitude = latitude;
		this.longitude = longitude;

		this.format = new DecimalFormat("##.######");
	}

	@JsonIgnore
	public String getLatitudeAsString() {
		return format.format(latitude);
	}

	@JsonIgnore
	public String getLongitudeAsString() {
		return format.format(longitude);
	}
}