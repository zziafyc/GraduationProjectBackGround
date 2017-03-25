package com.zzia.graduation.daoBean;

import org.springframework.stereotype.Repository;
import com.zzia.graduation.base.BaseDaoBean;
import com.zzia.graduation.dao.UserDao;
import com.zzia.graduation.model.User;

@Repository
public class UserDaoBean extends BaseDaoBean<User> implements UserDao {
	

}
