package org.wahlzeit.model;

import java.util.logging.Logger;

import org.wahlzeit.services.LogBuilder;

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
		return new SneakerPhoto(id);
	}
}
