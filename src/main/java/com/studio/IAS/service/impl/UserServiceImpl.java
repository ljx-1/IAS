package com.studio.IAS.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studio.IAS.dao.UserDao;
import com.studio.IAS.entity.User;
import com.studio.IAS.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Override
	public User userMatch(User user) {
		// TODO Auto-generated method stub
		//userDao.selectUser(user)!=null null==1是错误的表达式
		return userDao.selectUser(user);
	}
	@Override
	public boolean userRegister(User user) {
		// TODO Auto-generated method stub
		if(userDao.insertUser(user)<=0)
		    return false;
		else
			return true;
	}
	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		return userDao.queryUser();
	}

}
