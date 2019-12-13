package com.studio.IAS.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studio.IAS.dao.ImageDao;
import com.studio.IAS.entity.Image;
import com.studio.IAS.entity.User;
import com.studio.IAS.service.ImageService;

import net.coobird.thumbnailator.Thumbnails;

@Service
public class ImageServiceImpl implements ImageService {
	@Autowired
	private ImageDao imageDao;

	@Override
	public boolean addImage(Image image,String fileName) {
		// TODO Auto-generated method stub
		String addr;
		image.setCreateTime(new Date());
		addr="http://127.0.0.1:8080/IAS/resources/IAS-image/"+image.getCategory()+"/"+fileName;
		image.setAddr(addr);
		int effectedNum=imageDao.insertImage(image);
		if(effectedNum<=0) {
			System.out.println("图片创建失败!");
			return false;
		}
		else {
			System.out.println("图片创建成功!");
			return true;
		}
	}

	@Override
	public boolean generateThumbnail(InputStream thumbnailInputStream, String fileName, Image image) {
		// TODO Auto-generated method stub
		String realFileName=image.getName();//用户上穿图片文件的名称
		String extension=getFileExtension(fileName);//用户上传文件的扩展名  jpg  gif等
		String absoluteAddr="D:/download/apache-tomcat-9.0.26/webapps/IAS/resources/IAS-image/"+image.getCategory()+"/"+realFileName+extension;//图片文件的绝对路径
		File dest=new File(absoluteAddr);//新生成文件的路径
		//创建缩略图
		boolean bool=true;
		try {
			Thumbnails.of(thumbnailInputStream)
			          .size(200,200)
			          .outputQuality(0.8f)
			          .toFile(dest);
		}catch(IOException e) {
			bool=false;
			e.printStackTrace();
		}
		return bool;
	}
	private static String getFileExtension(String fileName) {
		//String originalFileName=cFile.getName();//文件名字
		return fileName.substring(fileName.lastIndexOf("."));//"."后的字符串，即扩展名
	}

	@Override
	public List<Image> getAllImage() {
		// TODO Auto-generated method stub
		return imageDao.queryAllImage();
	}

	@Override
	public List<Image> getImagesByUserId(User user) {
		// TODO Auto-generated method stub
		return imageDao.getImagesByUserId(user);
	}

	@Override
	public boolean deleteImageById(Integer id) {
		// TODO Auto-generated method stub
		if(imageDao.deleteImageById(id)>0)
			return true;
		else
			return false; 
	}
}
