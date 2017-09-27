package com.fleetnest.nestor.json;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import static java.time.format.DateTimeFormatter.ofPattern;

/**
 * LocalDateTime Json serializer
 * 
 * @author Cihad Baskoy
 */
public class DateTimeSerializer extends JsonSerializer<LocalDateTime> {

	@Override
	public void serialize(LocalDateTime dateTime, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException, JsonProcessingException {
		jsonGenerator.writeString(dateTime.format(ofPattern("yyyy-MM-dd HH:mm:ss")));
	}
}