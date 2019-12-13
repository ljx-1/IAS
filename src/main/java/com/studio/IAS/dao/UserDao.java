package com.studio.IAS.dao;

import java.util.List;

import com.studio.IAS.entity.User;

public interface UserDao {
	List<User> queryUser();//查询所有用户
	User selectUser(User user);//选择用户
	int insertUser(User user);//创建新用户
}
