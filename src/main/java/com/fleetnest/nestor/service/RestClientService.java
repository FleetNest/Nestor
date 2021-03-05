package com.fleetnest.nestor.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fleetnest.nestor.handler.NestorRestErrorHandler.FleetnestRestClientException;
import com.fleetnest.nestor.model.RestResponse;
import com.fleetnest.nestor.model.SensorData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Rest Client that sends the data to the FleetNest URI.
 * 
 * @author Cihad Baskoy
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class RestClientService {

	private static final String DEFAULT_SCHEME = "http";
	
	@Value("${server.host}") private String host;
	@Value("${server.port}") private int port;
	@Value("${device.data.path}") private String path;
	
	private final RestTemplate restTemplate;
	
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

	private String getUri() {
		return UriComponentsBuilder.newInstance()
				.scheme(DEFAULT_SCHEME)
				.host(host)
				.port(port)
				.path(path)
				.toUriString();
	}
}