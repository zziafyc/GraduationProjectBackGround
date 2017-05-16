package com.zzia.graduation.daoBean;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.zzia.graduation.base.BaseDaoBean;
import com.zzia.graduation.dao.UserDao;
import com.zzia.graduation.model.User;

@Repository
public class UserDaoBean extends BaseDaoBean<User> implements UserDao {

	@Override
	public List<User> searchUser(String key, String column1, String column2) {
		Query query = getSession().createQuery("from User where " + column1 + " like '%" + key + "%' or "+ column2 + " like '%" + key + "%'");
		List<User> list = query.list();
		if (list != null && !list.isEmpty()) {
			return list;
		} else {
			return null;
		}
	}

}
