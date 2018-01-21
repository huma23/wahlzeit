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


import org.wahlzeit.model.sneaker.Sneaker;

import com.googlecode.objectify.annotation.Serialize;
import com.googlecode.objectify.annotation.Subclass;

/**
 * Class for representing a photo of sneaker wahlzeit
 * @author huma23
 * 
 * Method calls that lead to a new object:
 * 		1) {@link SneakerPhotoManager#createPhoto(String, com.google.appengine.api.images.Image, Sneaker)}
 * 		2) {@link PhotoUtil#createPhoto(String, PhotoId, com.google.appengine.api.images.Image, Sneaker)}
 * 		3) {@link SneakerPhotoFactory#createPhoto(PhotoId, Sneaker)}
 * 
 * Object creation:
 * 		1) Delegation: 		Separate-object
 * 		2) Selection:		By-Subclassing
 * 		3) Configuration:	N/A
 * 		4) Instantiation:	In-Code
 * 		5) Initialization:	Default
 * 		6) Building:		Default
 *
 */
@Subclass
public class SneakerPhoto extends Photo {

	@Serialize
	private Sneaker sneaker = null;
	
	public SneakerPhoto() {
		super();
	}
	
	public SneakerPhoto(PhotoId id) {
		super(id);
	}
	
	public SneakerPhoto(Sneaker sneaker) {
		super();
		this.sneaker = sneaker;
	}
	
	public SneakerPhoto(PhotoId id, Sneaker sneaker) {
		super(id);
		this.sneaker = sneaker;
	}
	
	public Sneaker getSneaker() {
		return sneaker;
	}
}
