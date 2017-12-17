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

import java.util.logging.Logger;

import org.wahlzeit.services.LogBuilder;
import org.wahlzeit.utils.Assertions;

public class SneakerPhotoFactory extends PhotoFactory {
	
	private static final Logger log = Logger.getLogger(SneakerPhotoFactory.class.getName());
	protected static PhotoFactory instance = null;
	
	protected SneakerPhotoFactory() {
		// do nothing
	}

	public static void initialize() {
		getInstance(); // drops result due to getInstance() side-effects
	}
	
	public static synchronized PhotoFactory getInstance() {
		if (instance == null) {
			log.config(LogBuilder.createSystemMessage().addAction("setting generic SneakerPhotoFactory").toString());
			setInstance(new SneakerPhotoFactory());
		}

		return instance;
	}
	
	/**
	 * Method to set the singleton instance of PhotoFactory.
	 */
	protected static synchronized void setInstance(PhotoFactory photoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize PhotoFactory twice");
		}

		instance = photoFactory;
	}

	@Override
	public Photo createPhoto() {
		return new SneakerPhoto();
	}

	@Override
	public Photo createPhoto(PhotoId id) {
		Assertions.assertObjectIsNotNull("PhotoId", id);
		return new SneakerPhoto(id);
	}
	
	public Photo createPhoto(String brand, String color) {
		Assertions.assertObjectIsNotNull("Brand", brand);
		Assertions.assertObjectIsNotNull("Color", color);
		return new SneakerPhoto(brand, color);
	}
}
