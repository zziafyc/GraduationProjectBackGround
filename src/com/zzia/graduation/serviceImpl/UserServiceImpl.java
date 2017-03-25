package com.zzia.graduation.serviceImpl;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzia.graduation.base.Constants;
import com.zzia.graduation.rong.ApiRongClient;
import com.zzia.graduation.rong.FormatType;
import com.zzia.graduation.rong.SdkHttpResult;
import com.zzia.graduation.dao.UserDao;
import com.zzia.graduation.model.User;
import com.zzia.graduation.service.UserService;
import com.zzia.graduation.utils.StringUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUser() {
		return (List<User>) userDao.queryAll();
	}

	@Override
	public User getUser(Object column, Object value) {
		User user = userDao.queryOne(column, value);
		return user;
	}
	@Override
	public User getUser(Object column, Object value, Object column2, Object value2) {
		User user=userDao.queryOne(column, value, column2, value2);
		return user;
	}

	@Override
	public User register(User user) {

		String userId = StringUtils.getGUID();
		user.setUserId(userId);
		// 然后从RongYun后台获取token值
		try {
			SdkHttpResult result = ApiRongClient.getToken(user.getUserId(),
					StringUtils.subString(user.getRealName(), Constants.CommonObjects.SUBLENGTH), null, FormatType.json);
			if (result.getHttpCode() == 200) {
				// 表示获取token成功
				user.setToken(new JSONObject(result.getResult()).getString("token"));
				userDao.add(user);
				return user;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public int updateUser(Object column, Object value,Object updateColumn,Object updateValue) {

		return userDao.updateOneColumn(column, value, updateColumn, updateValue);
	}
	

}
