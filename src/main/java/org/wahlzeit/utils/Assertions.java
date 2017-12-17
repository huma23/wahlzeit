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
package org.wahlzeit.utils;

/**
 * This class combines all common assertion methods used in more than one class.
 * Specific assertion methods are in the concrete class. 
 */
public class Assertions {
	
	/**
	 * Simple implementation if value >= 0.
	 * @param value
	 * @return
	 */
	public static void assertValueNotNegative(String name, double value) {
		
		if(value < 0) {
			throw new IllegalStateException(name + "must be positive or zero!");
		}
	}
	
	/**
	 * Checks if the double is not a number or infinite.
	 * Returns true if it is not one of them.
	 * 
	 * @param val
	 * @return
	 */
	public static void assertValidDoubleValue(String name, double val) {
		
		if(Double.isNaN(val) || Double.isInfinite(val)) {
			throw new IllegalStateException(name + ": Double value can not be infinite or not a number!");
		}
	}
	
	/**
	 * Assertion-method for checking an input object if it is null.
	 * Throws IllegalArgumentException in case of null.
	 * @param coordinate
	 */
	public static void assertObjectIsNotNull(String name, Object obj) {
		if(obj == null) {
			throw new IllegalArgumentException(name + " can not be null!");
		}
	}
}
