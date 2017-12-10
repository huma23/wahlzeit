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

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import org.wahlzeit.services.LogBuilder;
import org.wahlzeit.utils.AssertionMethods;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Work;

public class SneakerPhotoManager extends PhotoManager {

	protected static final PhotoManager instance = new SneakerPhotoManager();
	
	private static final Logger log = Logger.getLogger(SneakerPhotoManager.class.getName());
	
	public static final PhotoManager getInstance() {
		return instance;
	}
	
	public SneakerPhotoManager() {
		photoTagCollector = SneakerPhotoFactory.getInstance().createPhotoTagCollector();
	}
	
	@Override
	public Photo getPhotoFromId(PhotoId id) {
		AssertionMethods.assertObjectIsNotNull("PhotoId", id);
		Photo result = doGetPhotoFromId(id);
		return result;
	}

	@Override
	public Photo getPhoto(PhotoId id) {
		AssertionMethods.assertObjectIsNotNull("PhotoId", id);
		return instance.getPhotoFromId(id);
	}	
	
	@Override
	public void loadPhotos() {
		Collection<Photo> existingPhotos = ObjectifyService.run(new Work<Collection<Photo>>() {
			@Override
			public Collection<Photo> run() {
				//Load exisiting with old entity
				Collection<Photo> existingPhotos = new ArrayList<Photo>();
				readObjects(existingPhotos, Photo.class);
				
				//Load new sneaker entities
				Collection<SneakerPhoto> newExistingPhotos = new ArrayList<SneakerPhoto>();
				readObjects(newExistingPhotos, SneakerPhoto.class);
				existingPhotos.addAll(newExistingPhotos);
				
				return existingPhotos;
			}
		});

		for (Photo photo : existingPhotos) {
			if (!doHasPhoto(photo.getId())) {
				log.config(LogBuilder.createSystemMessage().
						addParameter("Load Photo with ID", photo.getIdAsString()).toString());
				loadScaledImages(photo);
				doAddPhoto(photo);
			} else {
				log.config(LogBuilder.createSystemMessage().
						addParameter("Already loaded Photo", photo.getIdAsString()).toString());
			}
		}

		log.info(LogBuilder.createSystemMessage().addMessage("All photos loaded.").toString());	
	}
}
