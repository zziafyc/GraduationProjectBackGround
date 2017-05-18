package com.zzia.graduation.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zzia.graduation.base.Constants;
import com.zzia.graduation.rong.ApiRongClient;
import com.zzia.graduation.rong.FormatType;
import com.zzia.graduation.rong.SdkHttpResult;
import com.zzia.graduation.dao.FriendsDao;
import com.zzia.graduation.dao.UserDao;
import com.zzia.graduation.model.Friends;
import com.zzia.graduation.model.User;
import com.zzia.graduation.service.UserService;
import com.zzia.graduation.utils.CommentUtils;
import com.zzia.graduation.utils.DateUtils;
import com.zzia.graduation.utils.StringUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private FriendsDao friendsDao;

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
		User user = userDao.queryOne(column, value, column2, value2);
		return user;
	}

	@Override
	public User register(User user) {

		String userId = StringUtils.getGUID();
		user.setUserId(userId);
		user.setState(1);// 注册成功后，就进入了登录状态
		// 然后从RongYun后台获取token值
		try {
			SdkHttpResult result = ApiRongClient.getToken(user.getUserId(),
					StringUtils.subString(user.getNickName(), Constants.CommonObjects.SUBLENGTH), null,
					FormatType.json);
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
	public int updateUser(Object column, Object value, Object updateColumn, Object updateValue) {

		return userDao.updateOneColumn(column, value, updateColumn, updateValue);
	}

	@Override
	public Map<String, List<Object>> getAllFriends(String userId) throws Exception {
		List<User> list = new ArrayList<>();
		List<User> tempList = new ArrayList<>();
		List<Friends> list1 = friendsDao.queryAll("applicationId", userId, "state", 1);
		List<Friends> list2 = friendsDao.queryAll("applicationObjectId", userId, "state", 1);
		if (list1 != null && !list1.isEmpty()) {
			for (Friends model : list1) {
				User user = userDao.queryOne("userId", model.getApplicationObjectId());// 我是申请方
				if (user != null) {
					user.setRemark(model.getRemark1());
					list.add(user);
					tempList.add(user);
				}
			}

		}
		if (list2 != null && !list2.isEmpty()) {
			for (Friends model : list2) {
				User user = userDao.queryOne("userId", model.getApplicationId());// 我是被申请方
				if (user != null) {
					user.setRemark(model.getRemark2());
					int k = 0;
					if (tempList != null && tempList.size() > 0) {
						for (User temp : tempList) {
							k++;
							if (temp.getUserId().equals(user.getUserId())) {
								break;
							}else{
								if (k == tempList.size()) {
									list.add(user);
								}
							}
						}
						
					} else {
						list.add(user);
					}
				}
			}
		}
		if (!list.isEmpty()) {
			return CommentUtils.sortByFirstChar(list, "remark");
		} else {
			return null;
		}

	}

	@Override
	public boolean addFriend(Friends friend) {
		if (friend != null) {
			// 需要先判断有没有该记录，如果有该记录，判断该记录的状态，如果是0未处理，就更新时间，1的话前台已经处理
			Friends record = friendsDao.queryOne("applicationId", friend.getApplicationId(), "applicationObjectId",
					friend.getApplicationObjectId());
			if (record != null) {
				friendsDao.updateOneColumnByTwoColumn("applicationId", friend.getApplicationId(), "applicationObjectId",
						friend.getApplicationObjectId(), "applicationDate", DateUtils.getDataTime());

			} else {
				friend.setApplicationDate(DateUtils.getDataTime());
				friend.setState(0);
				friendsDao.add(friend);
			}
			return true;
		}
		return false;

	}

	@Override
	public boolean updateLoginState(User user) {
		if (user != null) {
			userDao.updateOneColumn("userId", user.getUserId(), "state", 0);
			return true;
		}
		return false;
	}

	@Override
	public List<User> searchUser(String key, String column1, String column2) {

		return userDao.searchUser(key, column1, column2);
	}

	@Override
	public List<User> getAllFriendsList(String userId) {
		List<User> list = new ArrayList<>();
		List<User> tempList = new ArrayList<>();
		List<Friends> list1 = friendsDao.queryAll("applicationId", userId, "state", 1);
		List<Friends> list2 = friendsDao.queryAll("applicationObjectId", userId, "state", 1);
		if (list1 != null && !list1.isEmpty()) {
			for (Friends model : list1) {
				User user = userDao.queryOne("userId", model.getApplicationObjectId());// 我是申请方
				if (user != null) {
					user.setRemark(model.getRemark1());
					list.add(user);
					tempList.add(user);
				}
			}

		}
		if (list2 != null && !list2.isEmpty()) {
			for (Friends model : list2) {
				User user = userDao.queryOne("userId", model.getApplicationId());// 我是被申请方
				if (user != null) {
					user.setRemark(model.getRemark2());
					int k = 0;
					if (tempList != null && tempList.size() > 0) {
						for (User temp : tempList) {
							k++;
							if (temp.getUserId().equals(user.getUserId())) {
								break;
							}else{
								if (k == tempList.size()) {
									list.add(user);
								}
							}
						}
						
					} else {
						list.add(user);
					}

				}

			}
		}
		if (!list.isEmpty()) {
			return list;
		} else {
			return null;
		}
	}

	@Override
	public boolean deleteFriendMessage(int id) {
		if (id > 0) {
			Friends friends = friendsDao.queryOne("id", id);
			if (friends.getState() == 0) {
				friendsDao.delete(id);
			} else {
				friendsDao.updateOneColumn("id", id, "isDelete", 1);
			}

			return true;
		}

		return false;
	}

	@Override
	public List<Friends> getAllFriendMessage(String userId) {
		List<Friends> friendsMessages = friendsDao.queryAllPageDesc("applicationObjectId", userId, "isDelete ", 0,
				"applicationDate");
		if (friendsMessages != null && friendsMessages.size() > 0) {
			for (Friends model : friendsMessages) {
				User user = userDao.queryOne("userId", model.getApplicationId());
				if (user != null) {
					model.setApplicationUser(user);
				}

			}
			return friendsMessages;
		}
		return null;
	}

	@Override
	public boolean changeMessageState(int id, String remark) {
		if (id > 0) {
			// 此时默认备注为对方昵称
			if (friendsDao.updateOneColumn("id", id, "state", 1) > 0
					&& friendsDao.updateOneColumn("id", id, "remark2", remark) > 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean changeRemark(int id, String remark) {
		if (friendsDao.updateOneColumn("id", id, "remark2", remark) > 0) {
			return true;
		}
		return false;
	}

}
