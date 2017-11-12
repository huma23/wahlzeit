package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SneakerPhotoManagerTest {
	
	private PhotoManager photomanager;
	private Photo photo1;
	private Photo photo2;
	private PhotoId id1;
	private PhotoId id5;

	@Before
	public void setup() {
		photomanager = SneakerPhotoManager.getInstance();
		id1 = new PhotoId(1);
		id5 = new PhotoId(5);
	}

	@Test
	public void testGetPhotoFromId() {
		photo1 = photomanager.getPhotoFromId(id1);
		photo2 = SneakerPhotoManager.getInstance().getPhotoFromId(id5);
		
		assertNotNull(photo1);
		assertTrue(photo1.getClass().isAssignableFrom(SneakerPhoto.class));
		assertNotNull(photo2);
		assertTrue(photo2.getClass().isAssignableFrom(SneakerPhoto.class));
	}

	@Test
	public void testGetInstance() {
		assertEquals(photomanager, SneakerPhotoManager.getInstance());
		assertEquals(SneakerPhotoManager.getInstance(), SneakerPhotoManager.getInstance());
		assertTrue(photomanager.getClass().isAssignableFrom(SneakerPhotoManager.class));
	}

}
