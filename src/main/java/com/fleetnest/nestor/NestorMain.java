package com.fleetnest.nestor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fleetnest.nestor.model.SensorData;
import com.fleetnest.nestor.service.DataService;
import com.fleetnest.nestor.service.RestClientService;

/**
 * Main class for Nestor Sample application
 * 
 * @author Cihad Baskoy
 */
public class NestorMain {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new AnnotationConfigApplicationContext(NestorConfig.class);
		
		RestClientService restClientService = context.getBean(RestClientService.class);
		DataService dataService = context.getBean(DataService.class);
		
		SensorData data = dataService.multipleDetailsData();
		restClientService.sendData(data);
	}
}