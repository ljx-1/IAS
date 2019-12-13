package com.studio.IAS.dao;

import java.util.List;

import com.studio.IAS.entity.Image;
import com.studio.IAS.entity.User;

public interface ImageDao {
	int insertImage(Image image);
	List<Image> queryAllImage();
	List<Image> getImagesByUserId(User user);
	int deleteImageById(Integer id); 
}
