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

import java.util.HashMap;
import java.util.Map;

import org.wahlzeit.utils.Assertions;

/**
 * This class represents the object manager of Sneaker entities and SneakerTypes
 *
 */
public class SneakerManager{

	private static final SneakerManager INSTANCE = new SneakerManager();
	private static final Map<Integer, Sneaker> SNEAKER_CACHE = new HashMap<Integer, Sneaker>();
	private static final Map<String, SneakerType> SNEAKER_TYPE_CACHE = new HashMap<String, SneakerType>();
	
	private SneakerManager() {}
	
	/**
	 * Singleton object of class SneakerManager
	 * @return
	 */
	public static synchronized SneakerManager getInstance() {
		return INSTANCE;
	}

	/**
	 * Creates a new entity of the class Sneaker with the given typename.
	 * Fails if the type does not exists.
	 * @param typeName
	 * @return
	 */
	public Sneaker createSneaker(String typeName) {
		SneakerType type = getSneakerType(typeName);
		Sneaker sneaker = type.createInstance(); //TODO attributes
		SNEAKER_CACHE.put(sneaker.hashCode(), sneaker);
		return sneaker;
	}
	
	/**
	 * Creates a new entity of the class SneakerType with the given parameters.
	 * Stores the new entity in the google datastore. Assertion of object creation is made
	 * by the constructor of SneakerType.
	 * @param typeName
	 * @return
	 */
	public SneakerType createSneakerType(String name, String brand, String collection, String color) {
		SneakerType type = new SneakerType(name, brand, collection, color);
		SNEAKER_TYPE_CACHE.put(type.getName(), type);
		return type;
	}
	
	/**
	 * Returns the SneakerType object with the given name
	 * @param typeName
	 * @return
	 */
	public SneakerType getSneakerType(String typeName) {
		assertValidTypeName(typeName);
		return SNEAKER_TYPE_CACHE.get(typeName);
	}
	
	/**
	 * Assertion for a valid SneakerType name.
	 * - not null
	 * - have to exists as key
	 * @param typeName
	 */
	private void assertValidTypeName(String typeName) {
		Assertions.assertObjectIsNotNull("TypeName", typeName);
		if(!SNEAKER_TYPE_CACHE.containsKey(typeName)) {
			throw new IllegalArgumentException("The typename does not exist!");
		}
	}

	/**
	 * Assertion for a valid sneaker object
	 * - not null
	 * - ClassInvariants
	 * @param sneaker
	 */
	public void assertValidSneaker(Sneaker sneaker) {
		Assertions.assertObjectIsNotNull("Sneaker", sneaker);
		sneaker.assertClassInvariants();
	}
	
	/**
	 * Assertion for a valid sneakerType object
	 * - not null
	 * - ClassInvariants
	 * @param sneaker
	 */
	public void assertValidSneakerType(SneakerType type) {
		Assertions.assertObjectIsNotNull("SneakerType", type);
		type.assertClassInvariants();
	}
}
