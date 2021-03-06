package com.zzia.graduation.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.zzia.graduation.model.Friends;
import com.zzia.graduation.model.User;

@Transactional
public interface UserService {

	// 得到所有用户信息
	public List<User> getAllUser();

	// 根据某一列得到某一个用户的详细信息
	public User getUser(Object column, Object value);

	// 根据某二列得到某一个用户的详细信息
	public User getUser(Object column, Object value,Object column2, Object value2);

	// 注册用户，传进来是用户信息
	public User register(User user);
	
	//修改用户的某个信息
	public int updateUser(Object column, Object value,Object updateColumn,Object updateValue);

	//获取好友列表
	public Map<String, List<Object>> getAllFriends(String userId) throws Exception;

	//添加好友
	public boolean addFriend(Friends friend);
	
	//修改登录状态
	public boolean updateLoginState(User user);
	
	//得到模糊查询的用户
	public List<User> searchUser(String key,String column1,String column2);
	
	//得到我的所有好友 
	public List<User> getAllFriendsList (String userId);
	
	//删除一条好友申请就记录
	public boolean deleteFriendMessage(int id);
	
	//得到我的所有好友申请信息
	public List<Friends> getAllFriendMessage(String userId,int currentPage,int count);
	
	//修改同意状态，完成加好友
	public boolean changeMessageState(int id,String remark);
	
	//修改同意状态，完成加好友
	public boolean changeRemark(int id,String remark);
}

