package com.fleetnest.nestor.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fleetnest.nestor.handler.NestorRestErrorHandler.FleetnestRestClientException;
import com.fleetnest.nestor.model.RestResponse;
import com.fleetnest.nestor.model.SensorData;

import lombok.extern.slf4j.Slf4j;

/**
 * Rest Client that sends the data to the FleetNest URI.
 * 
 * @author Cihad Baskoy
 */
@Slf4j
@Service
public class RestClientService {

	@Autowired
	private Environment env;

	@Autowired
	private RestTemplate restTemplate;
	
	public RestResponse sendData(SensorData sensorData) {
		RestResponse restResponse = null;

		try {
			log.info("Sending data for uniqueId {}", sensorData.getUniqueId());
			log.debug("Sending device data {}", sensorData);
			restResponse = restTemplate.postForObject(getUri(), sensorData, RestResponse.class);
		} catch (FleetnestRestClientException ex) {
			restResponse = ex.getRestResponse();
		}

		log.info("Response from server is : {}", restResponse);
		
		return restResponse;
	}

	private URI getUri() {
		URI uri = null;
		try {
			uri = new URI("http", null, env.getProperty("server.host"), env.getProperty("server.port", Integer.class), env.getProperty("device.data.path"), null, null);
		} catch (URISyntaxException exc) {
			log.warn("Cannot generate URI", exc);
		}

		return uri;
	}
}