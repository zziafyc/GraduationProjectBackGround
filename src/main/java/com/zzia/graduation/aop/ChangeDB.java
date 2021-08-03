package com.zzia.graduation.aop;

import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ChangeDB{
//	@Autowired
//	private UserDao userDao;
//	@Autowired
//	private FriendsDao friendsDao;
//	@Autowired
//	private PhotoConnectDao photoConnectDao;
//	@Autowired
//	private DiaryDao diaryDao;
//	@Autowired
//	private DiaryPraiseDao diaryPraiseDao;
//	@Autowired
//	private DiaryCommentDao diaryCommentDao;
//	@Autowired
//	private VideoConnectDao videoConnectDao;
//	@Autowired
//	private TravelPlanDao travelPlanDao;
//	@Autowired
//	TravelMemberDao travelMemberDao;
//	@Autowired
//	TravelRouteDao travelRouteDao;
//
//	@Pointcut("execution(* com.zzia.graduation.aop.UserDbImpl.recovery*(..))")
//	private void recoveryDB(){}
//
//	@Pointcut("execution(* com.zzia.graduation.action.*.*(..))")
//	private void anyOldTransfer(){}
//
//	@Before("anyOldTransfer()")
//	public void changedbbefore(){
//		SessionFactory sf = (SessionFactory) applicationContext.getBean("sessionFactory");
//		setFactory(sf);
//	}
//
//	@Before("recoveryDB()")
//	public void recoveryDb(){
//		SessionFactory sf = (SessionFactory) applicationContext.getBean("sessionFactory");
//		setFactory(sf);
//	}
//
//	public void setFactory(SessionFactory sf){
//		userDao.setSessionFactory(sf);
//		friendsDao.setSessionFactory(sf);
//		photoConnectDao.setSessionFactory(sf);
//		diaryDao.setSessionFactory(sf);
//		diaryPraiseDao.setSessionFactory(sf);
//		diaryCommentDao.setSessionFactory(sf);
//		videoConnectDao.setSessionFactory(sf);
//		travelPlanDao.setSessionFactory(sf);
//		travelMemberDao.setSessionFactory(sf);
//		travelRouteDao.setSessionFactory(sf);
//
//	}
}
