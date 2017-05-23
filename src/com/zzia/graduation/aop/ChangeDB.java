package com.zzia.graduation.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.zzia.graduation.dao.DiaryCommentDao;
import com.zzia.graduation.dao.DiaryDao;
import com.zzia.graduation.dao.DiaryPraiseDao;
import com.zzia.graduation.dao.FriendsDao;
import com.zzia.graduation.dao.PhotoConnectDao;
import com.zzia.graduation.dao.TravelMemberDao;
import com.zzia.graduation.dao.TravelPlanDao;
import com.zzia.graduation.dao.TravelRouteDao;
import com.zzia.graduation.dao.UserDao;
import com.zzia.graduation.dao.VideoConnectDao;
import com.zzia.graduation.model.TravelMember;
import com.zzia.graduation.model.TravelPlan;

@Aspect
public class ChangeDB extends AbstractJUnit4SpringContextTests{
	@Autowired
	private UserDao userDao;
	@Autowired
	private FriendsDao friendsDao;
	@Autowired
	private PhotoConnectDao photoConnectDao;
	@Autowired
	private DiaryDao diaryDao;
	@Autowired
	private DiaryPraiseDao diaryPraiseDao;
	@Autowired
	private DiaryCommentDao diaryCommentDao;
	@Autowired
	private VideoConnectDao videoConnectDao;
	@Autowired
	private TravelPlanDao travelPlanDao;
	@Autowired 
	TravelMemberDao travelMemberDao;
	@Autowired
	TravelRouteDao travelRouteDao;

	@Pointcut("execution(* com.zzia.graduation.aop.UserDbImpl.recovery*(..))")
	private void recoveryDB(){}
	
	@Pointcut("execution(* com.zzia.graduation.action.*.*(..))")
	private void anyOldTransfer(){}
	
	@Before("anyOldTransfer()")
	public void changedbbefore(){
		SessionFactory sf = (SessionFactory) applicationContext.getBean("sessionFactory");
		setFactory(sf);
	}
	
	@Before("recoveryDB()")
	public void recoveryDb(){
		SessionFactory sf = (SessionFactory) applicationContext.getBean("sessionFactory");
		setFactory(sf);
	}
	
	public void setFactory(SessionFactory sf){
		userDao.setSessionFactory(sf);
		friendsDao.setSessionFactory(sf);
		photoConnectDao.setSessionFactory(sf);
		diaryDao.setSessionFactory(sf);
		diaryPraiseDao.setSessionFactory(sf);
		diaryCommentDao.setSessionFactory(sf);
		videoConnectDao.setSessionFactory(sf);
		travelPlanDao.setSessionFactory(sf);
		travelMemberDao.setSessionFactory(sf);
		travelRouteDao.setSessionFactory(sf);
	
	}
}
