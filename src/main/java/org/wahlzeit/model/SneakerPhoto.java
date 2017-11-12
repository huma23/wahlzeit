package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

@Subclass(name="Photo", alsoLoad="Photo", index=true)
public class SneakerPhoto extends Photo {

	public SneakerPhoto() {
		super();
	}
	
	public SneakerPhoto(PhotoId id) {
		super(id);
	}
}
