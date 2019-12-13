package com.studio.IAS.web.imageadmin;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studio.IAS.entity.Image;
import com.studio.IAS.entity.User;
import com.studio.IAS.service.ImageService;
import com.studio.IAS.util.HttpServletRequestUtil;
import com.studio.IAS.util.ImageUtil;
import com.studio.IAS.web.useradmin.UserController;

@Controller
@RequestMapping(value="imageadmin")
public class ImageController {
	UserController userController=new UserController();
	@Autowired//只能是service
	private ImageService imageService;
	
	@RequestMapping(value="/imageshow",method= {RequestMethod.GET})
	public String imageShow() {
		return "image/image-show";
	}
	
	@RequestMapping(value="/uploadOperation",method= {RequestMethod.GET})
	public String imageUpload() {
		return "image/image-upload";
	}
	//所有图片
	@RequestMapping(value = "/listimage", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listImage(){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<Image> list= new ArrayList<Image>();
		try {
			list=imageService.getAllImage();
			modelMap.put("imageList", list);
            modelMap.put("total", list.size());
            modelMap.put("success",true);
		}catch(Exception e) {
			 e.printStackTrace();
	            modelMap.put("success", false);
	            modelMap.put("errMsg", e.toString());
		}
		return modelMap;
	}
	//用户的个人图片
	@RequestMapping(value = "/listimageByid", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listImageById(){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<Image> list= new ArrayList<Image>();
		try {
			System.out.println(userController instanceof UserController);
			System.out.println("当前用户是"+userController.currentUser);
			list=imageService.getImagesByUserId(userController.currentUser);
			modelMap.put("imageList", list);
            modelMap.put("total", list.size());
            modelMap.put("success",true);
		}catch(Exception e) {
			 e.printStackTrace();
	            modelMap.put("success", false);
	            modelMap.put("errMsg", e.toString());
		}
		return modelMap;
	}
	
	@RequestMapping(value="/registerImage",method=RequestMethod.POST)
	@ResponseBody//将返回的Java对象转化为json数据
	private String registerImage(HttpServletRequest request){
		//处理与图片有关的信息
		String imageInfo=HttpServletRequestUtil.getString(request, "imageInfo");
		ObjectMapper mapper=new ObjectMapper();
		Image image=null;
		try {
			image=mapper.readValue(imageInfo, Image.class);
		}catch(Exception e) {
			e.getMessage();
			
		}
		//处理图片
		CommonsMultipartFile Img=null;
		CommonsMultipartResolver commonsMultipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
		if(commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest) request;
			Img=(CommonsMultipartFile) multipartHttpServletRequest.getFile("image");
		}else {
			System.out.println("错误");
		}
		image.setName(Img.getOriginalFilename().substring(0, Img.getOriginalFilename().indexOf('.')));
		imageService.addImage(image,Img.getOriginalFilename());//传往数据库
		try {
			imageService.generateThumbnail(Img.getInputStream(),Img.getOriginalFilename(),image);//将图片存到电脑中
			System.out.println(Img.getOriginalFilename());
		} catch (IOException e) {
			// TODO Auto-generated catch block
		    e.getMessage();
		}
		return "redirect:imageadmin/listimageByid";
	}
	
	@RequestMapping(value="/deleteImageById",method=RequestMethod.GET)
	@ResponseBody
	public boolean deleteImageById(HttpServletRequest request) {
		
		System.out.println("id接受成功");
		String imageId=HttpServletRequestUtil.getString(request, "imageId");
		System.out.println("id有字符串转化整型成功");
		System.out.println(imageId.getClass().getName().toString());
		Integer Id=Integer.valueOf(imageId.trim());
		System.out.println(Id);	
		boolean bool=imageService.deleteImageById(Id);
		System.out.println(bool);
		return bool;
	}
}
