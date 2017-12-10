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

import com.googlecode.objectify.annotation.Subclass;

/**
 * Class for representing a photo of sneaker wahlzeit
 * @author huma23
 *
 */
@Subclass
public class SneakerPhoto extends Photo {
	
	private String brand;
	private String color;

	public SneakerPhoto() {
		super();
	}
	
	public SneakerPhoto(String brand, String color) {
		this();
		this.brand = brand;
		this.color = color;
	}
	
	public SneakerPhoto(PhotoId id) {
		super(id);
	}
	
	//Getter and setter
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}
