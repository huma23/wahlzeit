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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.wahlzeit.utils.Assertions;

/**
 * Representation of a SneakerType.
 */
public class SneakerType {
	
	protected SneakerType superType;
	protected Set<SneakerType> subTypes  = new HashSet<SneakerType>();;

	private String name;
	private String collection;
	private String brand;
	private String color;
	
	/**
	 * Constructor of a sneaker type. Only valid if used by SneakerManager
	 * @param name
	 * @param brand
	 * @param collection
	 * @param color
	 */
	public SneakerType(String name, String brand, String collection, String color) {
		this.name = name;
		this.brand = brand;
		this.collection = collection;
		this.color = color;
		assertClassInvariants();
	}
	
	/**
	 * Checks if the given type is a subtype of the current instance or a subtype
	 * of one of the subtypes
	 * 
	 * @param sneakerType
	 * @return
	 */
	public boolean isSubType(SneakerType sneakerType) {
		Assertions.assertObjectIsNotNull("SneakerType", sneakerType);
		
		if(subTypes.contains(sneakerType)) {
			return true;
		}
		
		for(SneakerType type : subTypes) {
			if(type.isSubType(sneakerType)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Returns the parent type
	 * @return
	 */
	public SneakerType getSuperType() {
		return superType;
	}
	
	/**
	 * Returns an iterator for each subtype
	 * @return
	 */
	public Iterator<SneakerType> getSubTypeIterator() {
		return subTypes.iterator();
	}
	
	/**
	 * Adds the given SneakerType as subtype to the current
	 * @param sneakerType
	 */
	public void addSubType(SneakerType sneakerType) {
		Assertions.assertObjectIsNotNull("SneakerType", sneakerType);
		sneakerType.superType = this;
		subTypes.add(sneakerType);
	}
	
	/**
	 * Removes the given type from the subtypes
	 * @param sneakerType
	 */
	public void removeSubType(SneakerType sneakerType) {
		Assertions.assertObjectIsNotNull("SneakerType", sneakerType);
		
		if(subTypes.contains(sneakerType)) {
			sneakerType.superType = null;
			subTypes.remove(sneakerType);	
		}
	}
	
	/**
	 * Checks if the given sneaker is an instance of this type
	 * or one of its subtypes
	 * @param sneaker
	 * @return
	 */
	public boolean hasInstance(Sneaker sneaker) {
		Assertions.assertObjectIsNotNull("Sneaker", sneaker);
		
		if(sneaker.getType().equals(this)) {
			return true;
		}
		
		for(SneakerType type : subTypes) {
			if(type.hasInstance(sneaker)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Creates a new instance of a sneaker with the current subtypes
	 * @return
	 */
	public Sneaker createInstance() {
		return new Sneaker(this);
	}
	
	/**
	 * Checks for a valid representation of this class.
	 * Throws an exception if a member is null.
	 */
	public void assertClassInvariants() {
		Assertions.assertObjectIsNotNull("Name", name);
		Assertions.assertObjectIsNotNull("Collection", collection);
		Assertions.assertObjectIsNotNull("Brand", brand);
		Assertions.assertObjectIsNotNull("Color", color);
	}

	/*
	 * Getters
	 */
	
	public String getName() {
		return name;
	}

	public String getCollection() {
		return collection;
	}

	public String getBrand() {
		return brand;
	}

	public String getColor() {
		return color;
	}
	/*
	 * End Getters
	 */
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		
		SneakerType type = (SneakerType) obj;
		return type.name.equals(name) && type.brand.equals(brand) && 
				type.collection.equals(collection) && type.color.equals(color);
 	}
}
