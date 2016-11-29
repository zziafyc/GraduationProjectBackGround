package com.zzia.graduation.daoBean;


import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.zzia.graduation.base.BaseDaoBean;
import com.zzia.graduation.dao.UserDao;
import com.zzia.graduation.model.User;


@Repository
public class UserDaoBean extends BaseDaoBean<User> implements UserDao{

	@Override
	public User queryByUserName(String userName) {
		Query query = getSession().createQuery("from User where userName=?");
		query.setParameter(0, userName);
		if (query.list() != null && !query.list().isEmpty()) {
			return (User) query.list().get(0);
		} else {
			return null;
		}
	}

	@Override
	public User queryByNameAndPass(String username, String pass) {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery("from User where userName=? and pass=?");
		query.setParameter(0, username);
		query.setParameter(1, pass);
		if (query.list() != null && !query.list().isEmpty()) {
			return (User) query.list().get(0);
		} else {
			return null;
		}
	}

	@Override
	public void addDepartment(String userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDepartment(String userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteDepartment(String userId) {
		// TODO Auto-generated method stub
		
	}
	
}
