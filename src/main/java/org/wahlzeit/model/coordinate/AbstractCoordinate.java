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

public abstract class AbstractCoordinate implements Coordinate {
	
	public static final double EPSILON = 0.00001;
	
	@Override
	public abstract CartesianCoordinate asCartesianCoordinate();
	
	@Override
	public abstract SphericCoordinate asSphericCoordinate();
	
	@Override
	public abstract boolean isEqual(Coordinate coordinate);

	/**
	 * Calculates the cartesian distance by converting the current object and the given Coordinate
	 * to cartesian coordinates.
	 */
	@Override
	public double getCartesianDistance(Coordinate coordinate) {

		assertCoordinateIsNotNull(coordinate);
		
		CartesianCoordinate currentCoordinate = this.asCartesianCoordinate();
		CartesianCoordinate compareCoordinate = coordinate.asCartesianCoordinate();

		double squarresSum = Math.pow(compareCoordinate.getX() - currentCoordinate.getX(), 2) 
				+ Math.pow(compareCoordinate.getY() - currentCoordinate.getY(), 2) 
				+ Math.pow(compareCoordinate.getZ() - currentCoordinate.getZ(), 2); 
		double distance = Math.sqrt(squarresSum);
		
		return distance;
	}

	/**
	 * Calculates the spheric distance by converting the current object and the given Coordinate
	 * to spheric coordinates.
	 */
	@Override
	public double getSphericDistance(Coordinate coordinate) {
		
		assertCoordinateIsNotNull(coordinate);
		
		SphericCoordinate currentCoordinate = this.asSphericCoordinate();
		SphericCoordinate compareCoordinate = coordinate.asSphericCoordinate();
		
		double radiantPhi1 = Math.toRadians(currentCoordinate.getLatitude());
		double radiantPhi2 = Math.toRadians(compareCoordinate.getLatitude());
		double radiantLambda1 = Math.toRadians(currentCoordinate.getLongitude());
		double radiantLambda2 = Math.toRadians(compareCoordinate.getLongitude());
		
		double angle = Math.acos(Math.sin(radiantPhi1) * Math.sin(radiantPhi2) + Math.cos(radiantPhi1) * Math.cos(radiantPhi2) * Math.cos(radiantLambda1 - radiantLambda2));
		
		//assuming that the radius of the current is the correct
		double distance = angle * currentCoordinate.getRadius();
		
		return distance;
	}

	/**
	 * Calculates the distance between the current and given Coordinate cord
	 * @param coord
	 * @return (double) distance
	 */
	@Override
	public double getDistance(Coordinate coordinate) {
		
		assertCoordinateIsNotNull(coordinate);
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
	
	/**
	 * Assertion-method for checking an input coordinate if it is null.
	 * Throws IllegalArgumentException in case of null.
	 * @param coordinate
	 */
	protected void assertCoordinateIsNotNull(Coordinate coordinate) {
		if(coordinate == null) {
			throw new IllegalArgumentException("Coordinate can not be null!");
		}
	}
}
