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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SneakerManagerTest {
	

	protected SneakerManager manager = SneakerManager.getInstance();
	
	@Test
	public void testGetInstance() {
		assertEquals(manager, SneakerManager.getInstance());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCreateSneaker() {
		Sneaker sneaker = manager.createSneaker("wrongType");
	}

	@Test
	public void testCreateSneakerType() {
		SneakerType type = manager.createSneakerType("testt", "test2", "test3", "test4");
		SneakerType type2 = manager.getSneakerType("testt");
		assertEquals(type, type2);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testAssertValidSneaker() {
		manager.assertValidSneaker(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testAssertValidSneakerType() {
		SneakerType type = new SneakerType("test", "test2", "test3", null);
		SneakerType type2 = new SneakerType("test", "test2", null, "test3");
		SneakerType type3 = new SneakerType("test", null, "test3", "test2");
		SneakerType type4 = new SneakerType(null, "test2", "test3", "test1");
		manager.assertValidSneakerType(type);
		manager.assertValidSneakerType(type2);
		manager.assertValidSneakerType(type3);
		manager.assertValidSneakerType(type4);
	}

}
