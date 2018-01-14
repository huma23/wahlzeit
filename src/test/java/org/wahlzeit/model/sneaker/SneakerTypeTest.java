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
package org.wahlzeit.model.sneaker;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SneakerTypeTest {

	protected SneakerManager manager = SneakerManager.getInstance();
	protected SneakerType type = new SneakerType("test", "test2", "test3", "");
	protected SneakerType type2 = new SneakerType("test", "test2", "", "test3");
	protected SneakerType type3 = new SneakerType("test", "", "test3", "test2");
	protected SneakerType type4 = new SneakerType("", "", "test3", "test2");
	protected SneakerType type5 = new SneakerType("", "test33", "test3", "test2");
	
	@Before
	public void setUp() {;
		type2.addSubType(type);
		type.addSubType(type3);
		type4.addSubType(type5);
		type4.addSubType(type5);
	}
	
	@Test
	public void testIsSubType() {
		assertTrue(type2.isSubType(type3));
		assertTrue(type2.isSubType(type));
		assertFalse(type4.isSubType(type));
	}

	@Test
	public void testGetSuperType() {
		assertTrue(type.getSuperType().equals(type2));
		assertTrue(type3.getSuperType().equals(type));
	}

	@Test
	public void testAddSubType() {
		assertTrue(type4.isSubType(type5));
		
		try {
			type5.addSubType(null);
			fail();
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}

	
	@Test(expected= IllegalArgumentException.class)
	public void testAssertClassInvariants() {
		SneakerType type = new SneakerType("test", "test2", "test3", null);
		SneakerType type2 = new SneakerType("test", "test2", null, "test3");
		SneakerType type3 = new SneakerType("test", null, "test3", "test2");
		SneakerType type4 = new SneakerType(null, "test2", "test3", "test1");
	}

}
