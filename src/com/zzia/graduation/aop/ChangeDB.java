package com.zzia.graduation.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.zzia.graduation.dao.UserDao;

@Aspect
public class ChangeDB extends AbstractJUnit4SpringContextTests{
	@Autowired
	private UserDao userDao;

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
	
	}
}
