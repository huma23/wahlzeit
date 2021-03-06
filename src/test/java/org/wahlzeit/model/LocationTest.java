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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.model.coordinate.CartesianCoordinate;
import org.wahlzeit.model.coordinate.Coordinate;
import org.wahlzeit.model.coordinate.SphericCoordinate;

/**
 * Test case for the Location class
 * @author huma23
 *
 */
public class LocationTest {

	protected Coordinate c0; //(1.0, 0.0, 0.0)
	protected Coordinate c1; //(2.0, 0.1, 1.0)
	protected Coordinate c2; //x:1.0, y:0.0, z: 0.0
	protected Coordinate c3;// Spherical (Latitude : 49.59099, Longitude: 11.00783)
	protected Location l1; //x:1.0, y:0.0, z: 0.0
	protected Location l2; //x:2.0, y:0.1, z: 1.0
	protected Location l3; //initialized with Coordinate c
	protected Location l4; // initialized with Spheric Coordinate
;	
	@Before
	public void setup() {
		c0 = CartesianCoordinate.createCartesianCoordinate(1.0, 0.0, 0.0);
		l1 = new Location(c0);
		c1 = CartesianCoordinate.createCartesianCoordinate(2.0, 0.1, 1.0);
		l2 = new Location(c1);
		c2 = CartesianCoordinate.createCartesianCoordinate(1.0, 0.0, 0.0);
		l3 = new Location(c2);
		c3 = SphericCoordinate.createSphericCoordinate(49.59099, 11.00783);
		l4 = new Location(c3);
	}
	
	@Test
	public void testEquals() {
		assertTrue(l1.equals(l1));
		assertTrue(l1.equals(l3));
		assertFalse(l1.equals(l2));
	}
	
	@Test
	public void testGetCoordinate() {
		assertEquals(c2, l3.getCoordinate());
		assertEquals(c2, l1.getCoordinate());
		assertNotEquals(c2, l2.getCoordinate());
	}
	
	@Test
	public void testToString() {
		assertEquals("Location (x / y / z): (1,00 / 0,00 / 0,00)", l1.toString());
		assertEquals("Location (x / y / z): (2,00 / 0,10 / 1,00)", l2.toString());
		assertEquals("Location (x / y / z): (1,00 / 0,00 / 0,00)", l3.toString());
		assertEquals("Location (latitude / longitude / radius): (49,59 / 11,01 / 6378,00)", l4.toString());
	}
}
