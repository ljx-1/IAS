package com.studio.IAS.web.useradmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studio.IAS.entity.User;
import com.studio.IAS.util.HttpServletRequestUtil;
import com.studio.IAS.service.UserService;

@Controller
@RequestMapping(value="useradmin")
public class UserController {
	public static User currentUser;//当前正在操作的用户
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/userlogon",method= {RequestMethod.GET})
	public String UserOperation() {
		return "user/user-logon";
	}
	
	@RequestMapping(value="/index",method= {RequestMethod.GET})
	public String UserIndex() {
		return "index";
	}
	
	@RequestMapping(value="/userregister",method= {RequestMethod.GET})
	public String UserLogon() {
		return "user/user-register";
	}
	
	@RequestMapping(value="/logonfailure",method= {RequestMethod.GET})
	public String logonFailure() {
		return "user/failure";
	}
	
	@RequestMapping(value = "/listuser", method = RequestMethod.GET)
    @ResponseBody//只要有数据返回前端就要加这个标签
    private Map<String, Object> listUser(){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<User> list= new ArrayList<User>();
		try {
			list=userService.getUserList();
			modelMap.put("rows", list);
            modelMap.put("total", list.size());
		}catch(Exception e) {
			 e.printStackTrace();
	            modelMap.put("success", false);
	            modelMap.put("errMsg", e.toString());
		}
		return modelMap;
	}
	@RequestMapping(value = "/currentuser", method = RequestMethod.GET)
    @ResponseBody//只要有数据返回前端就要加这个标签
    private Map<String, Object> currentUser(){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		System.out.println("使用了currentUser函数！");
		try {
			modelMap.put("currentUser", currentUser);
		}catch(Exception e) {
			 e.printStackTrace();
	            modelMap.put("success", false);
	            modelMap.put("errMsg", e.toString());
		}
		return modelMap;
	}
	
	@RequestMapping(value="/userLogon",method=RequestMethod.POST)
    @ResponseBody
	private boolean UserRegister(HttpServletRequest request){
		System.out.println("数据传入成功！");
		String userStr=HttpServletRequestUtil.getString(request, "userStr");
		ObjectMapper mapper=new ObjectMapper();
		User user=null;
		try {
			 user=mapper.readValue(userStr,User.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentUser=userService.userMatch(user);
		System.out.println(user.getAccount()+" "+user.getPassword());
		System.out.println(currentUser);
		if(currentUser==null)
			return false;
		if(currentUser instanceof User) {
			System.out.println("登陆成功!");
			System.out.println(currentUser.getId()+" "+currentUser.getName()+" "+currentUser.getGender());
			return true;
		}	
		else {
			System.out.println("登陆失败!");
			return false;
		}
	}
	
	@RequestMapping(value="/userRegister",method=RequestMethod.POST)
    @ResponseBody
	private boolean Userregister(HttpServletRequest request) {
		System.out.println("数据传入成功！");
		String userStr=HttpServletRequestUtil.getString(request, "userStr");
		ObjectMapper mapper=new ObjectMapper();
		User user=null;
		try {
			 user=mapper.readValue(userStr,User.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setCreateTime(new Date());
		System.out.println(user.getCreateTime());
		System.out.println(user.getName()+" "+user.getGender()+" "+user.getAccount()+" "+user.getPassword()+" "+user.getRemark());
		if(userService.userMatch(user) instanceof User) {
			System.out.println("该用用户已存在!");
			return false;
		}
		if(userService.userRegister(user)) {
			System.out.println("注册成功！");
			return true;
		}
		else {
			System.out.println("注册失败！");
			return false;
		}
	}
}
