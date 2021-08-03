package com.zzia.graduation.dao;

import java.util.List;

import com.zzia.graduation.base.BaseDao;
import com.zzia.graduation.model.User;

public interface UserDao extends BaseDao<User> {
	// 通过关键词key,搜索用户，方便加好友
	public List<User> searchUser(String key,String column1,String column2);

}
