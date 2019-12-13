package com.studio.IAS.util;

import com.studio.IAS.entity.Image;



import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	
	public void generateThumbnail(InputStream thumbnailInputStream,String fileName,Image image){
		String realFileName=image.getName();//用户上穿图片文件的名称
		String extension=getFileExtension(fileName);//用户上传文件的扩展名  jpg  gif等
		String absoluteAddr="C:/Users/海平/Desktop/IAS-image/"+image.getCategory()+"/"+realFileName+extension;//图片文件的绝对路径
		File dest=new File(absoluteAddr);//新生成文件的路径
		//创建缩略图
		try {
			Thumbnails.of(thumbnailInputStream)
			          .size(200,200)
			          .outputQuality(0.8f)
			          .toFile(dest);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String getFileExtension(String fileName) {
		//String originalFileName=cFile.getName();//文件名字
		return fileName.substring(fileName.lastIndexOf("."));//"."后的字符串，即扩展名
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String basePath=Thread.currentThread().getContextClassLoader().getResource("").getPath();
		basePath=URLDecoder.decode(basePath,"utf-8");
		Thumbnails.of(new File("C:/Users/海平/Desktop/image/海绵宝宝.jpg"))//传入需要处理的图片文件
		          .size(400,400)//指定输出图片的大小
		          .outputQuality(0.8f)//压缩
		          .toFile("C:/Users/海平/Desktop/IAS-image/character/海绵宝宝.jpg");//输出到
	}

}
