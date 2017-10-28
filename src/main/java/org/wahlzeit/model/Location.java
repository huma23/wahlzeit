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
package org.wahlzeit.model;

/**
 * The class Location represents the location of a photo.
 *
 */
public class Location {

	protected Coordinate coordinate;
	
	public Location(double x, double y, double z) {
		this.coordinate = new Coordinate(x, y, z);
	}
	
	public Location(Coordinate coord) {
		this.coordinate = coord;
	}
	
	public Coordinate getCoordinate() {	
		return coordinate;
	}
	
	/**
	 * Compares its only attribute Coordinate with the given object.
	 * Returns true if it is equal.
	 */
	@Override
	public boolean equals(Object obj) {
		
		if(obj != null && obj instanceof Location){
			return coordinate.equals(((Location)obj).coordinate);
		}
		
		return false;
	}
	
	/**
	 * Creates a string of the attribute Coordinate and extends it with
	 * the class name at the beginning. 
	 */
	@Override
	public String toString() {
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Location ");
		stringBuilder.append(coordinate.toString());
		return stringBuilder.toString();
	}
}
