package com.fleetnest.nestor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * RestResponse that is received from the FleetNest application
 * 
 * @author Cihad Baskoy
 */
@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class RestResponse {

	private Long transactionId;
	private Integer httpResponseCode;
	private String title;
	private String description;
	private Integer errorCode;
}