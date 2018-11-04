package com.fleetnest.nestor;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.fleetnest.nestor.model.SensorData;
import com.fleetnest.nestor.service.DataService;
import com.fleetnest.nestor.service.RestClientService;

import static org.springframework.boot.Banner.Mode.OFF;
import static org.springframework.boot.WebApplicationType.NONE;

/**
 * Main class for Nestor Sample application
 * 
 * @author Cihad Baskoy
 */
public class NestorMain {
	
	public static void main(String[] args) throws Exception {
		
		ConfigurableApplicationContext context = new SpringApplicationBuilder(NestorConfig.class)
				.web(NONE)
				.bannerMode(OFF)
				.run();
		
		RestClientService restClientService = context.getBean(RestClientService.class);
		DataService dataService = context.getBean(DataService.class);
		
		SensorData data = dataService.multipleDetailsData();
		restClientService.sendData(data);
	}
}