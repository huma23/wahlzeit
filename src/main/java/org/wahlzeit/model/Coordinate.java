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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * A coordinate represents one cartesian coordinate
 *
 */
public class Coordinate {

	private double x;
	private double y;
	private double z;
	
	public Coordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Calculates the distance between the current and given Coordinate cord
	 * @param coord
	 * @return (double) distance
	 */
	public double getDistance(Coordinate coord) {
		
		double squarresSum = Math.pow(coord.x - x, 2) + Math.pow(coord.y - y, 2) + Math.pow(coord.z - z, 2); 
		double distance = Math.sqrt(squarresSum);
		
		return distance;
	}
	
	/**
	 * Compares the values of each axis and returns true if equal.
	 * @param coordinate
	 * @return
	 */
	public boolean isEqual(Coordinate coordinate) {
		
		return 	x == coordinate.x && 
				y == coordinate.y && 
				z == coordinate.z;
	}
	
	/**
	 * Forwards the given object to method isEqual if obj is an instance of Coordinate
	 */
	@Override
	public boolean equals(Object obj) {
		
		if(obj == null) {
			return false;
		}
		
		if(obj instanceof Coordinate) {
			return isEqual((Coordinate) obj);
		}
		
		return false;
	}
	
	/**
	 * Returns a string with the format: "(x / y / z)"
	 * The  values are represented with two decimals.
	 */
	@Override
	public String toString() {
		
		DecimalFormatSymbols symbol = new DecimalFormatSymbols();
		symbol.setDecimalSeparator(',');
		symbol.setGroupingSeparator('.');
		DecimalFormat format = new DecimalFormat("#0.00", symbol);
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("(x / y / z): (");
		stringBuilder.append(format.format(x));
		stringBuilder.append(" / ");
		stringBuilder.append(format.format(y));
		stringBuilder.append(" / ");
		stringBuilder.append(format.format(z));
		stringBuilder.append(")");
		return stringBuilder.toString();
	}
}
