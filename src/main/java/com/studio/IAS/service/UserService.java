package com.studio.IAS.service;

import java.util.List;

import com.studio.IAS.entity.User;

public interface UserService {
	User userMatch(User user);
	boolean userRegister(User user);
	List<User> getUserList();
}
