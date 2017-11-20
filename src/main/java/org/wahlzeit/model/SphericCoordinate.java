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

public class SphericCoordinate implements Coordinate {
	
	public static final double EPSILON = 0.00001;
	public static final double EARTH_RADIUS = 6378.0;
	
	private double latitude;
	private double longitude;
	private double radius;

	public SphericCoordinate(double latitude, double longitude, double radius) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
	}
	
	public SphericCoordinate(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = EARTH_RADIUS;
	}

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		
		double radiantPhi = Math.toRadians(this.latitude);
		double radiantLambda = Math.toRadians(this.longitude);
		
		double x = this.radius * Math.sin(radiantLambda) * Math.cos(radiantPhi);
		double y = this.radius * Math.sin(radiantLambda) * Math.sin(radiantPhi);
		double z = this.radius * Math.cos(radiantLambda);
		
		return new CartesianCoordinate(x, y, z);
	}

	@Override
	public double getCartesianDistance(Coordinate coordinate) {
		
		return asCartesianCoordinate().getDistance(coordinate);
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		
		return this;
	}

	@Override
	public double getSphericDistance(Coordinate coordinate) {
		
		SphericCoordinate sphericCoordinate = coordinate.asSphericCoordinate();
		
		double radiantPhi1 = Math.toRadians(this.latitude);
		double radiantPhi2 = Math.toRadians(sphericCoordinate.latitude);
		double radiantLambda1 = Math.toRadians(this.longitude);
		double radiantLambda2 = Math.toRadians(sphericCoordinate.longitude);
		
		double angle = Math.acos(Math.sin(radiantPhi1) * Math.sin(radiantPhi2) + Math.cos(radiantPhi1) * Math.cos(radiantPhi2) * Math.cos(radiantLambda1 - radiantLambda2));
		
		//assuming that the radius of the current is the correct
		double distance = angle * radius;
		
		return distance;
	}

	@Override
	public double getDistance(Coordinate coordinate) {
		
		return getCartesianDistance(coordinate);
	}

	@Override
	public boolean isEqual(Coordinate coordinate) {
		
		if(coordinate == null) {
			return false;
		}
		
		return Math.abs(this.longitude - coordinate.asSphericCoordinate().longitude) <= EPSILON 
				&& Math.abs(this.latitude - coordinate.asSphericCoordinate().latitude) <= EPSILON 
				&& Math.abs(this.radius - coordinate.asSphericCoordinate().radius) <= EPSILON;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(radius);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Coordinate) {
			return isEqual((Coordinate) obj);
		}
			
		return false;
	}
	
	@Override
	public String toString() {
		DecimalFormatSymbols symbol = new DecimalFormatSymbols();
		symbol.setDecimalSeparator(',');
		symbol.setGroupingSeparator('.');
		DecimalFormat format = new DecimalFormat("#0.00", symbol);
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("(latitude / longitude / radius): (");
		stringBuilder.append(format.format(latitude));
		stringBuilder.append(" / ");
		stringBuilder.append(format.format(longitude));
		stringBuilder.append(" / ");
		stringBuilder.append(format.format(radius));
		stringBuilder.append(")");
		return stringBuilder.toString();
	}
}
