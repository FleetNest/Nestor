package com.fleetnest.nestor.model;

import java.text.DecimalFormat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A class to represent a latitude and longitude
 * 
 * @author Cihad Baskoy
 */
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

	public Float getLatitude() {
		return latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	@JsonIgnore
	public String getLatitudeAsString() {
		return format.format(latitude);
	}

	@JsonIgnore
	public String getLongitudeAsString() {
		return format.format(longitude);
	}

	public String toString() {
		return format.format(latitude) + ", " + format.format(longitude);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		return true;
	}
}