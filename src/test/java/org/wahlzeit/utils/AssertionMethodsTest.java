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

import org.junit.Test;
import org.wahlzeit.model.coordinate.CartesianCoordinate;

public class AssertionMethodsTest {

	@Test
	public void testAssertValueNotNegativeNoException() {
		AssertionMethods.assertValueNotNegative("test1", 0);
		AssertionMethods.assertValueNotNegative("test2", 10.5);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testAssertValueNotNegativeException() {
		AssertionMethods.assertValueNotNegative("test1", -1.0);
		AssertionMethods.assertValueNotNegative("test2", -10.0);
	}
	
	@Test
	public void testAssertValidDoubleValueNoException() {
		AssertionMethods.assertValidDoubleValue("test1", 0.0);
		AssertionMethods.assertValidDoubleValue("test2", 5.2);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testAssertValidDoubleValueException() {
		AssertionMethods.assertValidDoubleValue("test1", Double.NaN);
		AssertionMethods.assertValidDoubleValue("test2", Double.POSITIVE_INFINITY);
	}
	
	@Test
	public void testAssertObjectIsNotNullNoException() {
		AssertionMethods.assertObjectIsNotNull("test1", new Object());
		AssertionMethods.assertObjectIsNotNull("test2", new CartesianCoordinate(3, 1, 2));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAssertObjectIsNotNullException() {
		AssertionMethods.assertObjectIsNotNull("test1", null);
	}
}
