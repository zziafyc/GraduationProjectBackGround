package com.zzia.graduation.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzia.graduation.dao.UserDao;
import com.zzia.graduation.model.User;
import com.zzia.graduation.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public User getUserByUserName(String userName) {
		User user= userDao.queryByUserName(userName);
		return user;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUser(){
		return (List<User>) userDao.queryAll();
	}

	@Override
	public List<User> getUsers(String userId){
		User user = userDao.query(userId);
		List<User> users = new ArrayList<User>();
		if (user.getIfAdmin() == 1) {	
			users = getAllUser();
		} 
		return users;
	}

	@Override
	public void addUser(String userName, String pass) {
		User user = new User();
		user.setUserId(UUID.randomUUID().toString());
		user.setUserName(userName);
		user.setPass(pass);
		userDao.add(user);
	}
	
	@Override
	public boolean checkUserName(String userName){
		User user = userDao.queryByUserName(userName);
		return user == null ? true:false;
	}

	@Override
	public User getUser(String username,String pass) {
		return userDao.queryByNameAndPass(username,pass);
	}
	
	
	
	
}
