package com.zzia.graduation.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zzia.graduation.model.User;
@Transactional
public interface UserService {

	User getUserByUserName(String userName);

	List<User> getAllUser();
	
	List<User> getUsers(String userId);
	
	void addUser(String userName, String pass);

	boolean checkUserName(String userName);

	User getUser(String userName,String pass);

}
