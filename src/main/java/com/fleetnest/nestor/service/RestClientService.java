package com.fleetnest.nestor.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fleetnest.nestor.exception.FleetnestRestClientException;
import com.fleetnest.nestor.model.RestResponse;
import com.fleetnest.nestor.model.SensorData;

/**
 * Rest Client that sends the data to the FleetNest URI.
 * 
 * @author Cihad Baskoy
 */
@Service
public class RestClientService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestClientService.class);

	@Autowired
	private Environment env;

	@Autowired
	private RestTemplate restTemplate;
	
	public RestResponse sendData(SensorData sensorData) {
		RestResponse restResponse = null;

		try {
			LOGGER.info("Sending data for uniqueId {}", sensorData.getUniqueId());
			LOGGER.debug("Sending device data {}", sensorData);
			restResponse = restTemplate.postForObject(getUri(), sensorData, RestResponse.class);
		} catch (FleetnestRestClientException ex) {
			restResponse = ex.getRestResponse();
		}

		LOGGER.info("Response from server is : {}", restResponse);
		
		return restResponse;
	}

	private URI getUri() {
		URI uri = null;
		try {
			uri = new URI("http", null, env.getProperty("server.host"), env.getProperty("server.port", Integer.class), env.getProperty("device.data.path"), null, null);
		} catch (URISyntaxException exc) {
			LOGGER.warn("Cannot generate URI", exc);
		}

		return uri;
	}
}