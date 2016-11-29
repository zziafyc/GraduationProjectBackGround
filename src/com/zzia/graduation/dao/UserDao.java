package com.zzia.graduation.dao;

import com.zzia.graduation.base.BaseDao;
import com.zzia.graduation.model.User;

public interface UserDao extends BaseDao<User>{

	User queryByUserName(String userName);

	User queryByNameAndPass(String username, String pass);
	
	void addDepartment(String userId);

	void updateDepartment(String userId);

	void deleteDepartment(String userId);

	
}
