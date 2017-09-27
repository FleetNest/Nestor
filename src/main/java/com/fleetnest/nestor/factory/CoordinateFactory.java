package com.fleetnest.nestor.factory;

import org.springframework.stereotype.Service;

import com.fleetnest.nestor.model.Coordinate;

import static io.generators.core.Generators.positiveInts;

import io.generators.core.Generator;

/**
 * Generates a coordinate object around a specific area [41.00000f,42.00000f]-[29.00000f,30.00000f]
 * 
 * @author Cihad Baskoy
 */
@Service
public class CoordinateFactory implements Generator<Coordinate> {

	@Override
	public Coordinate next() {

		float latitude = positiveInts(4100000, 4200000).next().floatValue()/100000;
		float longitude = positiveInts(2900000, 3000000).next().floatValue()/100000;

		return new Coordinate(latitude, longitude); 
	}
}