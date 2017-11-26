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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.model.coordinate.CartesianCoordinate;
import org.wahlzeit.model.coordinate.Coordinate;
import org.wahlzeit.model.coordinate.SphericCoordinate;
/**
 * Test case for the SphericCoordinate class
 * @author huma23
 *
 */
public class SphericCoordinateTest {
	
	protected Coordinate c0; // Spherical (Lat: 52.52437, Long: 13.41053)
	protected Coordinate c1; // Cartesian: Same as c0 (899.9979354	1173.93367, 204.092799)
	protected Coordinate c2; // Spherical (Lat: 48.13743, Long: 11.57549)
	protected Coordinate c3; // Cartesian: Same as c2 (854.071104, 953.1297086, 6248.278988)
	protected Coordinate c4; // Spherical (Latitude : 49.59099, Longitude: 11.00783)
	protected Coordinate c5; // Cartesian: Same as c4 (789.4491653, 927.3041463, 6260.651806)
	
	@Before
	public void setup() {
		c0 = new SphericCoordinate(52.52437, 13.41053);
		c1 = new CartesianCoordinate(899.9979354, 1173.93367, 6204.092799);
		c2 = new SphericCoordinate(48.13743, 11.57549);
		c3 = new CartesianCoordinate(854.071104, 953.1297086, 6248.278988);
		c4 = new SphericCoordinate(49.59099, 11.00783);
		c5 = new CartesianCoordinate(789.4491653, 927.3041463, 6260.651806);
	}
	
	/**
	 * Test for convertion to a cartesian coordinate
	 */
	@Test
	public void testAsCartesianCoordinate() {
		assertEquals(c1, c0.asCartesianCoordinate());
		assertEquals(c3, c2.asCartesianCoordinate());
		assertEquals(c5, c4.asCartesianCoordinate());
		assertEquals(c1, c1.asSphericCoordinate().asCartesianCoordinate());
	}

	/**
	 * Test for getting the direct distance
	 */
	@Test
	public void testGetCartesianDistance() {
		assertEquals(229.8174, c0.getCartesianDistance(c2), 0.0001);
		assertEquals(70.68268, c2.getCartesianDistance(c4), 0.0001);
		
		try {
			c2.getCartesianDistance(null);
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
		assertEquals(c0, c0.asSphericCoordinate());
		assertEquals(c0, c1.asSphericCoordinate());
		assertEquals(c4, c4.asSphericCoordinate());
		assertEquals(c4, c5.asSphericCoordinate());
	}

	/**
	 * Test for getting the spheric distance
	 */
	@Test
	public void testGetSphericDistance() {
		assertEquals(167.05915, c4.getSphericDistance(c2), 0.0001);
		assertEquals(167.05915, c5.getSphericDistance(c3), 0.0001);
		assertEquals(505.40613, c1.getSphericDistance(c3), 0.0001);
		assertEquals(367.21814, c4.getSphericDistance(c0), 0.0001);
		
		try {
			c4.getSphericDistance(null);
			fail("Expected IllegalargumentException");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("Coordinate can not be null!", e.getMessage());
		}
	}

	/**
	 * Test distance between two coordinates, with a delta of 0.0001.
	 */
	@Test
	public void testGetDistance() {
		assertEquals(229.8174, c0.getDistance(c3), 0.0001);
		assertEquals(70.68268, c2.getDistance(c4), 0.0001);
		assertEquals(0, c4.getDistance(c5), 0.0001);
		
		try {
			c0.getDistance(null);
			fail("Expected IllegalargumentException");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("Coordinate can not be null!", e.getMessage());
		}
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
		assertFalse(c1.equals(c4));
		assertFalse(c0.equals(c3));
		assertFalse(c0.equals(null));
	}

	/**
	 * Test the string output
	 */
	@Test
	public void testToString() {
		assertEquals("(latitude / longitude / radius): (49,59 / 11,01 / 6378,00)", c4.toString());
		assertEquals("(latitude / longitude / radius): (48,14 / 11,58 / 6378,00)", c2.toString());
	}

}
