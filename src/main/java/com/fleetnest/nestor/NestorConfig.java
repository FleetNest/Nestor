package com.fleetnest.nestor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This is the main configuration class for spring.
 * 
 * @author Cihad Baskoy
 */
@Configuration
@PropertySource("classpath:nestor.properties")
public class NestorConfig {

    @Autowired
    private ResponseErrorHandler responseErrorHandler;

	/**
	 * Returns the rest template as bean
	 */
	@Bean
	public RestTemplate restTemplate() {

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		restTemplate.setErrorHandler(responseErrorHandler);

		return restTemplate;
	}

	/**
	 * Returns the object mapper
	 */
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
}