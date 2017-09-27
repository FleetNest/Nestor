package com.fleetnest.nestor.generator;

import org.springframework.http.HttpStatus;

import static io.generators.core.Generators.randomEnum;
import static java.util.Arrays.asList;

import io.generators.core.Generator;

/**
 * Generates a random HTTP status excluding the given list
 * 
 * @author Cihad Baskoy
 */
public class HttpStatusGenerator implements Generator<HttpStatus> {

	private final Generator<HttpStatus> httpStatusGenericGenerator;
	private final HttpStatus[] exclusions;

	public HttpStatusGenerator(HttpStatus... exclusions) {
		this.exclusions = exclusions;
		httpStatusGenericGenerator = randomEnum(HttpStatus.class);
	}
	
	@Override
	public HttpStatus next() {
		
		HttpStatus generatedValue = httpStatusGenericGenerator.next();
		while(asList(exclusions).contains(generatedValue)) {
			generatedValue = httpStatusGenericGenerator.next();
		}
		
		return generatedValue;
	}
}