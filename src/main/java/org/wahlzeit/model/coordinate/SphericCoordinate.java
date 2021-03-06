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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Map;

import org.wahlzeit.utils.Assertions;

/**
 * A SphericCoordinate represents one coordinate in a spheric space
 */
public class SphericCoordinate extends AbstractCoordinate {
	
	public static final double EARTH_RADIUS = 6378.0;
	
	private final double latitude;
	private final double longitude;
	private final double radius;
	private final static Map<Integer, SphericCoordinate> SHARED_COORDINATES = new HashMap<>();

	/**
	 * Checks if the current Spheric value already exists and returns it.
	 * Otherwise it creates a new value and returns it.
	 * @param latitude
	 * @param longitude
	 * @param radius
	 * @return
	 */
	public static SphericCoordinate createSphericCoordinate(double latitude, double longitude, double radius) {
		SphericCoordinate tempSphericCoordinate = new SphericCoordinate(latitude, longitude, radius);
		return createCoordinate(SHARED_COORDINATES, tempSphericCoordinate);
	}
	
	/**
	 * Checks if the current Cartesian value already exists and returns it.
	 * Otherwise it creates a new value and returns it. The parameter radius of the spheric coordinate
	 * is set to the default (Earth Radius).
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public static SphericCoordinate createSphericCoordinate(double latitude, double longitude) {
		return createSphericCoordinate(latitude, longitude, EARTH_RADIUS);
	}
	
	/**
	 * Constructor for creating an instance of a SphericCoordinate
	 * @param latitude
	 * @param longitude
	 * @param radius
	 */
	private SphericCoordinate(double latitude, double longitude, double radius) {
		
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
		assertClassInvariants();
	}
	
	/**
	 * Converts this SphericCoordinate to a CartesianCoordinate.
	 */
	@Override
	protected CartesianCoordinate doAsCartesianCoordinate() {
		
		double radiantPhi = Math.toRadians(this.latitude);
		double radiantLambda = Math.toRadians(this.longitude);
		
		double x = this.radius * Math.sin(radiantLambda) * Math.cos(radiantPhi);
		double y = this.radius * Math.sin(radiantLambda) * Math.sin(radiantPhi);
		double z = this.radius * Math.cos(radiantLambda);
		
		return CartesianCoordinate.createCartesianCoordinate(x, y, z);
	}

	/**
	 * Returns the current object, because it is already an instance of a SquericCoordinate
	 */
	@Override
	protected SphericCoordinate doAsSphericCoordinate() {	
		return this;
	}

	/**
	 * Compares the values of latitude, longitude and radius. Returns true if equal.
	 * @param coordinate
	 * @return
	 */
	@Override
	protected boolean doIsEqual(Coordinate coordinate) {
		
		return this.hashCode() == coordinate.hashCode();
	}
	
	@Override
	protected double doGetCartesianDistance(Coordinate coordinate) {
		
		return this.asCartesianCoordinate().doGetCartesianDistance(coordinate);
	}
	
	@Override
	protected double doGetSphericDistance(Coordinate coordinate) {
		
		SphericCoordinate compareCoordinate = coordinate.asSphericCoordinate();
		
		double radiantPhi1 = Math.toRadians(this.getLatitude());
		double radiantPhi2 = Math.toRadians(compareCoordinate.getLatitude());
		double radiantLambda1 = Math.toRadians(this.getLongitude());
		double radiantLambda2 = Math.toRadians(compareCoordinate.getLongitude());
		
		double angle = Math.acos(Math.sin(radiantPhi1) * Math.sin(radiantPhi2) + 
				Math.cos(radiantPhi1) * Math.cos(radiantPhi2) * Math.cos(radiantLambda1 - radiantLambda2));
		
		//assuming that the radius of the current is the correct
		double distance = angle * this.getRadius();
		
		return distance;
	}

	/**
	 * Generated by eclipse
	 */
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
	
	/**
	 * Returns a string with the format: "(latitude / longitude / radius)"
	 * The  values are represented with two decimals.
	 */
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
	
	@Override
	public void assertClassInvariants() {
		
		Assertions.assertValueNotNegative("Radius", radius);
		Assertions.assertValidDoubleValue("Latitude", latitude);
		Assertions.assertValidDoubleValue("Longitude", longitude);
		assertValidLongitudeAndLatitude(longitude, latitude);
	}
	
	protected void assertValidLongitudeAndLatitude(double langitude, double latitude) {
		
		if(latitude < -90.0 || latitude > 90.0) {
			throw new IllegalStateException("Latitude value must be between -90 and 90!");
		}
		
		if(longitude < -180.0 || longitude > 180.0) {
			throw new IllegalStateException("Longitude value must be between -180 and 180!");
		}
	}

	/*
	 * Getters
	 */
	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getRadius() {
		return radius;
	}
}
