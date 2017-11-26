/*
 * Author: huma23, github.com/huma23
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * Test case for the CartesianCoordinate class
 * @author huma23
 *
 */
public class CartesianCoordinateTest {

	protected Coordinate c0; // 0.0, 0.0, 0.0 -> All values same
	protected Coordinate c1; // 1.1, 2.5, 3.0 -> All values different
	protected Coordinate c2; // -3.0, -3.5, -6.4 -> All values negative
	protected Coordinate c3; // 2.2, 10.0, -5.8 -> Some positive/negative 
	protected Coordinate c4; // Spherical (Latitude : 49.59099, Longitude: 11.00783)
	protected Coordinate c5; // Cartesian: Same as c4 (789.4491653, 927.3041463, 6260.651806)
	protected Coordinate c6; // Spherical (Lat: 48.13743, Long: 11.57549)
	protected Coordinate c7; // Cartesian: Same as c6 (854.071104, 953.1297086, 6248.278988)
	
	@Before
	public void setup() {
		c0 = new CartesianCoordinate(0.0, 0.0, 0.0);
		c1 = new CartesianCoordinate(1.1, 2.5, 3.0);
		c2 = new CartesianCoordinate(-3.0, -3.5, -6.4);
		c3 = new CartesianCoordinate(2.2, 10.0, -5.8);
		c4 = new SphericCoordinate(49.59099, 11.00783);
		c5 = new CartesianCoordinate(789.4491653, 927.3041463, 6260.651806);
		c6 = new SphericCoordinate(48.13743, 11.57549);
		c7 = new CartesianCoordinate(854.071104, 953.1297086, 6248.278988);
	}
	
	/**
	 * Some random comparisons between the given coordinates
	 * for the Coordinate#isEqual(Coordinate) method
	 */
	@Test
	public void testIsEqual() {
		assertTrue(c0.isEqual(c0));
		assertTrue(c1.isEqual(c1));
		assertTrue(c3.asSphericCoordinate().isEqual(c3));
		assertTrue(c1.isEqual(c1.asSphericCoordinate()));
		assertFalse(c2.isEqual(c0));
		assertFalse(c3.isEqual(c1));
		assertFalse(c2.asSphericCoordinate().isEqual(c0));
		assertFalse(c3.isEqual(c1.asSphericCoordinate()));
		
		try {
			c0.isEqual(null);
			fail("Expected IllegalargumentException");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("Coordinate can not be null!", e.getMessage());
		}
	}
	

	/**
	 * Some random comparisons between the given coordinates
	 * for the Coordinate#equals(Coordinate) method
	 */
	@Test
	public void testEquals() {
		assertTrue(c3.equals(c3));
		assertTrue(c2.equals(c2));
		assertFalse(c1.equals(c0));
		assertFalse(c0.equals(c3));
		
		//equals method accepts any object -> should be false if its no instance of Coordinate
		assertFalse(c0.equals(null));
	}

	/**
	 * Test distance between two coordinates, with a delta of 0.0000001.
	 * The distance was calculated by hand with the formula sqrt((x2-x1)^2 + (y2-y1)^2 + (z2-z1)^2)
	 */
	@Test
	public void testGetDistance() {
		assertEquals(4.0570925, c0.getDistance(c1), 0.0000001);
		assertEquals(11.8814982, c1.getDistance(c2), 0.0000001);
		assertEquals(14.4792955, c2.getDistance(c3), 0.0000001);
		
		try {
			c0.getDistance(null);
			fail("Expected IllegalargumentException");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("Coordinate can not be null!", e.getMessage());
		}
	}
	
	/**
	 * Test string output
	 */
	@Test
	public void testToString() {
		assertEquals("(x / y / z): (0,00 / 0,00 / 0,00)", c0.toString());
		assertEquals("(x / y / z): (1,10 / 2,50 / 3,00)", c1.toString());
		assertEquals("(x / y / z): (2,20 / 10,00 / -5,80)", c3.toString());
	}
	
	/**
	 * Test the convertion to a cartesian coordinate.
	 */
	@Test
	public void testAsCartesianCoordinate() {
		assertEquals(c0, c0.asCartesianCoordinate());
		assertEquals(c4, c5.asSphericCoordinate().asCartesianCoordinate());
		assertEquals(c2, c2.asCartesianCoordinate());
		assertEquals(c6, c7.asSphericCoordinate().asCartesianCoordinate());
	}
	
	/**
	 * Test for getting the direct distance
	 */
	@Test
	public void testGetCartesianDistance() {
		assertEquals(4.0570925, c0.getCartesianDistance(c1), 0.0000001);
		assertEquals(11.8814982, c1.getCartesianDistance(c2), 0.0000001);
		assertEquals(14.4792955, c2.getCartesianDistance(c3), 0.0000001);
		
		try {
			c1.getCartesianDistance(null);
			fail("Expected IllegalargumentException");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("Coordinate can not be null!", e.getMessage());
		}
	}
	
	/**
	 * Test for calculating to spheric distance
	 */
	@Test
	public void testGetSphericDistance() {
		assertEquals(167.05915, c4.getSphericDistance(c6), 0.0001);
		assertEquals(167.05915, c5.getSphericDistance(c6), 0.0001);
		
		try {
			c4.getSphericDistance(null);
			fail("Expected IllegalargumentException");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("Coordinate can not be null!", e.getMessage());
		}
	}
	
	/**
	 * Test for convertion to a spheric coordinate
	 */
	@Test
	public void testAsSphericCoordinate() {
		assertEquals(c4, c5.asSphericCoordinate());
		assertEquals(c6, c7.asSphericCoordinate());
		assertNotEquals(c4, c7.asSphericCoordinate());
		assertNotEquals(c6, c5.asSphericCoordinate());
	}
}
