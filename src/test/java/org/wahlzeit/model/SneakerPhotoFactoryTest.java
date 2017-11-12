package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SneakerPhotoFactoryTest {

	private PhotoFactory photoFactory;
	
	@Before
	public void setup(){
		photoFactory = SneakerPhotoFactory.getInstance();
	}

	@Test
	public void testCreatePhoto() {
		Photo photo = photoFactory.createPhoto();
		
		assertNotNull(photo);
		assertTrue(photo.getClass().isAssignableFrom(SneakerPhoto.class));
	}

	@Test
	public void testCreatePhotoPhotoId() {
		PhotoId photoId1 = new PhotoId(1);
		PhotoId photoId5 = new PhotoId(5);
		Photo photo1 = photoFactory.createPhoto(photoId1);
		Photo photo5 = photoFactory.createPhoto(photoId5);
		
		assertNotNull(photo1);
		assertTrue(photo1.getClass().isAssignableFrom(SneakerPhoto.class));
		assertNotNull(photo5);
		assertTrue(photo5.getClass().isAssignableFrom(SneakerPhoto.class));
	}

	@Test
	public void testGetInstance() {
		assertEquals(photoFactory, SneakerPhotoFactory.getInstance());
		assertEquals(SneakerPhotoFactory.getInstance(), SneakerPhotoFactory.getInstance());
		assertTrue(photoFactory.getClass().isAssignableFrom(SneakerPhotoFactory.class));
	}

}
