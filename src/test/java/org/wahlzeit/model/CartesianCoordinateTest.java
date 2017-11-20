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
package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test case for the Coordinate class
 * @author huma23
 *
 */
public class CoordinateTest {

	protected Coordinate c0; // 0.0, 0.0, 0.0 -> All values same
	protected Coordinate c1; // 1.1, 2.5, 3.0 -> All values different
	protected Coordinate c2; // -3.0, -3.5, -6.4 -> All values negative
	protected Coordinate c3; // 2.2, 10.0, -5.8 -> Some positive/negative 
	
	@Before
	public void setup() {
		c0 = new Coordinate(0.0, 0.0, 0.0);
		c1 = new Coordinate(1.1, 2.5, 3.0);
		c2 = new Coordinate(-3.0, -3.5, -6.4);
		c3 = new Coordinate(2.2, 10.0, -5.8);
	}
	
	/**
	 * Some random comparisons between the given coordinates
	 * for the Coordinate#isEqual(Coordinate) method
	 */
	@Test
	public void testIsEqual() {
		assertTrue(c0.isEqual(c0));
		assertTrue(c1.isEqual(c1));
		assertFalse(c2.isEqual(c0));
		assertFalse(c3.isEqual(c1));
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
	}
	
	@Test
	public void testToString() {
		assertEquals("(x / y / z): (0,00 / 0,00 / 0,00)", c0.toString());
		assertEquals("(x / y / z): (1,10 / 2,50 / 3,00)", c1.toString());
		assertEquals("(x / y / z): (2,20 / 10,00 / -5,80)", c3.toString());
	}
}
