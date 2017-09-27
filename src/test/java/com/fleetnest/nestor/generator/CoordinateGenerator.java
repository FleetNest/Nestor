package com.fleetnest.nestor.generator;

import com.fleetnest.nestor.model.Coordinate;

import static io.generators.core.Generators.positiveInts;

import io.generators.core.Generator;

/**
 * Generates a coordinate object around a specific area [41.00000f,42.00000f]-[29.00000f,30.00000f]
 * 
 * @author Cihad Baskoy
 */
public class CoordinateGenerator implements Generator<Coordinate> {

	private final Generator<Integer> latitudeGenerator;
	private final Generator<Integer> longitudeGenerator;

	public CoordinateGenerator() {
		latitudeGenerator = positiveInts(4100000, 4200000);
		longitudeGenerator = positiveInts(2900000, 3000000);
	}
	
	@Override
	public Coordinate next() {
		return new Coordinate(latitudeGenerator.next().floatValue()/100000, longitudeGenerator.next().floatValue()/100000); 
	}
}