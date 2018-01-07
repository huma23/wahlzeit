/* Author: huma23, github.com/huma23
 * 
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package org.wahlzeit.model.coordinate;

import java.util.Map;

import org.wahlzeit.annotations.PatternInstance;
import org.wahlzeit.utils.Assertions;

@PatternInstance(
	patternName = "Value Object",
	participants = {
		"AbstractCoordinate",
		"CartesianCoordinate",
		"SphericCoordinate"
	}
)
public abstract class AbstractCoordinate implements Coordinate {
	
	public static final double EPSILON = 0.00001;

	/**
	 * Class invariant specific for each class.
	 * Needs to be implemented
	 * @return
	 */
	public abstract void assertClassInvariants();
	
	/**
	 * Actual convertion to a cartesian Coordinate needs to be
	 * implemented in the subclass
	 * @return
	 */
	protected abstract CartesianCoordinate doAsCartesianCoordinate();
	
	/**
	 * Actual convertion to a spheric Coordinate needs to be implemented
	 * in the subclass
	 * @return
	 */
	protected abstract SphericCoordinate doAsSphericCoordinate();
	
	/**
	 * Implementation of the IsEqual check
	 * @param coordinate
	 * @return
	 */
	protected abstract boolean doIsEqual(Coordinate coordinate);
	
	/**
	 * Implementation of the cartesian distance between to distances
	 * @param coordinate
	 * @return
	 */
	protected abstract double doGetCartesianDistance(Coordinate coordinate);
	
	/**
	 * Implementation of the spheric distance between to distances
	 * @param coordinate
	 * @return
	 */
	protected abstract double doGetSphericDistance(Coordinate coordinate);
	
	/**
	 * Generic Method for checking if this Coordinate already exists. If not then
	 * the new one will be added to the map.
	 * @param sharedMap
	 * @param coordinate
	 * @return
	 */
	protected static <T extends Coordinate> T createCoordinate(Map<Integer, T> sharedMap, T coordinate){
		
		T sharedCoordinate = sharedMap.get(coordinate.hashCode());
		
		if(sharedCoordinate == null) {
			sharedMap.put(coordinate.hashCode(), coordinate);
			return coordinate;
		} else {
			return sharedCoordinate;
		}
	}
	
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		
		//Class Invariants
		assertClassInvariants();
								
		//Pre Conditions - none	
								
		//MethodCall
		CartesianCoordinate coordinate = doAsCartesianCoordinate();
								
		//Post Conditions - none
								
		//Class Invariants
		assertClassInvariants();
						
		return coordinate;
	}
	
	@Override
	public SphericCoordinate asSphericCoordinate() {
		
		//Class Invariants
		assertClassInvariants();
						
		//Pre Conditions - none
						
		//MethodCall
		SphericCoordinate coordinate = doAsSphericCoordinate();
						
		//Post Conditions - none
						
		//Class Invariants
		assertClassInvariants();
				
		return coordinate;
	}
	
	@Override
	public boolean isEqual(Coordinate coordinate) {
		
		//Class Invariants
		assertClassInvariants();
				
		//Pre Conditions
		Assertions.assertObjectIsNotNull("Coordinate", coordinate);
				
		//MethodCall
		boolean isEqual = doIsEqual(coordinate);
				
		//Post Conditions - none
				
		//Class Invariants
		assertClassInvariants();
		
		return isEqual;
	}

	/**
	 * Calculates the cartesian distance by converting the current object and the given Coordinate
	 * to cartesian coordinates.
	 */
	@Override
	public double getCartesianDistance(Coordinate coordinate) {

		//Class Invariants
		assertClassInvariants();
		
		//Pre Conditions
		Assertions.assertObjectIsNotNull("Coordinate", coordinate);
		
		//MethodCall
		double distance = doGetCartesianDistance(coordinate);
		
		//Post Conditions -none
		Assertions.assertValueNotNegative("Distanz", distance);
		
		//Class Invariants
		assertClassInvariants();	
		
		return distance;
	}

	/**
	 * Calculates the spheric distance by converting the current object and the given Coordinate
	 * to spheric coordinates.
	 */
	@Override
	public double getSphericDistance(Coordinate coordinate) {
		
		//Class Invariants
		assertClassInvariants();
		
		//Pre Conditions
		Assertions.assertObjectIsNotNull("Coordinate", coordinate);
		
		//MethodCall
		double distance = doGetSphericDistance(coordinate);
		
		//Post Conditions
		Assertions.assertValueNotNegative("Distanz", distance);
		
		//Class Invariants
		assertClassInvariants();	
		
		return distance;
	}

	/**
	 * Calculates the distance between the current and given Coordinate cord
	 * @param coord
	 * @return (double) distance
	 */
	@Override
	public double getDistance(Coordinate coordinate) {
		
		return getCartesianDistance(coordinate);
	}

	/**
	 * Checks if the given object is an instance of Coordinate and if this is true it calls the
	 * {@link Coordinate#isEqual(Coordinate)} method. Otherwise false is returned.
	 */
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Coordinate) {
			return isEqual((Coordinate) obj);
		}
			
		return false;
	}
}
