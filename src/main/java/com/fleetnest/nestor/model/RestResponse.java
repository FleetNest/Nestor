package com.fleetnest.nestor.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * RestResponse that is received from the FleetNest application
 * 
 * @author Cihad Baskoy
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class RestResponse {

	private Long transactionId;
	private Integer httpResponseCode;
	private String title;
	private String description;
	private Integer errorCode;
	
	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getHttpResponseCode() {
		return httpResponseCode;
	}
	
	public void setHttpResponseCode(Integer httpResponseCode) {
		this.httpResponseCode = httpResponseCode;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}