package org.wahlzeit.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import org.wahlzeit.services.LogBuilder;

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
		if (id == null) {
			return null;
		}

		Photo result = doGetPhotoFromId(id);

		if (result == null) {
			result = SneakerPhotoFactory.getInstance().loadPhoto(id);
			if (result != null) {
				doAddPhoto(result);
			}
		}

		return result;
	}

	@Override
	public Photo getPhoto(PhotoId id) {
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
