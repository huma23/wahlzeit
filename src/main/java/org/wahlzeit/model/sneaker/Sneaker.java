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

import org.wahlzeit.utils.Assertions;

/**
 * Representation of a Sneaker with a SneakerType
 * 
 * Method calls that lead to a new object:
 * 		1) {@link SneakerManager#createSneaker(String)}
 * 		2) {@link SneakerType#createInstance()}
 * 
 * Object creation:
 * 		1) Delegation: 		Separate-object
 * 		2) Selection:		On-the-spot
 * 		3) Configuration:	N/A
 * 		4) Instantiation:	In-Code
 * 		5) Initialization:	Default
 * 		6) Building:		Default
 */
public class Sneaker {

	protected SneakerType type;
	
	/**
	 * Constructor used by the SneakerManager and SneakerType
	 * @param type
	 */
	public Sneaker(SneakerType type) {
		this.type = type;
		assertClassInvariants();
	}
	
	/**
	 * Checks for a valid representation of this class.
	 * Throws an exception if the type is null.
	 */
	public void assertClassInvariants() {
		Assertions.assertObjectIsNotNull("SneakerType", type);
	}
	
	/*
	 * Getters
	 */
	public SneakerType getType() {
		return type;
	}
	
	public String getName() {
		return type.getName();
	}
	
	public String getBrand() {
		return type.getBrand();
	}
	
	public String getCollection() {
		return type.getCollection();
	}
	
	public String getColor() {
		return type.getColor();
	}
}
