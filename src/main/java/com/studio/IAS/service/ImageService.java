package com.studio.IAS.service;

import java.io.InputStream;

import java.util.List;

import com.studio.IAS.entity.Image;
import com.studio.IAS.entity.User;

public interface ImageService {
	boolean addImage(Image image,String fileName);
	boolean generateThumbnail(InputStream thumbnailInputStream,String fileName,Image image);
	List<Image> getAllImage();
	List<Image> getImagesByUserId(User user);
	boolean deleteImageById(Integer id);	
}
