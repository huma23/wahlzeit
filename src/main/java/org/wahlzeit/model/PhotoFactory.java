/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
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

import org.wahlzeit.annotations.PatternInstance;

/**
 * An Abstract Factory for creating photos and related objects.
 */
@PatternInstance(
		patternName = "AbstractFactory",
		participants = {
			"PhotoFactory", 
			"SneakerPhotoFactory"
		}
	)
public abstract class PhotoFactory {

	/**
	 * @methodtype factory
	 */
	public abstract Photo createPhoto();

	/**
	 * Creates a new photo with the specified id
	 */
	public abstract Photo createPhoto(PhotoId id);

	/**
	 *
	 */
	public PhotoFilter createPhotoFilter() {
		return new PhotoFilter();
	}

	/**
	 *
	 */
	public PhotoTagCollector createPhotoTagCollector() {
		return new PhotoTagCollector();
	}

}
