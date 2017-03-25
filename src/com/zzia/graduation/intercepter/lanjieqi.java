package com.zzia.graduation.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.zzia.graduation.aop.UserDb;
import com.zzia.graduation.dao.UserDao;
import com.zzia.graduation.model.User;

public class lanjieqi implements Interceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*@Autowired
	private UserDao userDao;*/
	
	@Autowired
	private UserDb userDB;
	
	@Override
	public void destroy() {
		userDB.recoveryDB();
	}

	@Override
	public void init() {
		userDB.recoveryDB();
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		return arg0.invoke();
	}

	/*@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		HttpServletRequest request = (HttpServletRequest) arg0.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		String path = request.getServletPath();
		String userId = request.getParameter("userId");
		User user = userDao.query(userId);
		if (path.equals("/loginAction_login")) {
			return arg0.invoke();
		} else {
			HttpSession session = request.getSession();
			User user1 = (User) session.getAttribute(user.getUserName());
			if (user1 != null) {
				return arg0.invoke();
			} else {
//				request.setAttribute("login", 0);
				user.setRemark("1");
				userDao.update(user1);
				return arg0.invoke();
			}
		}
	}*/

	
}
