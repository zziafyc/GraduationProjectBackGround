package com.zzia.graduation.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.zzia.graduation.model.User;
import com.zzia.graduation.service.UserService;

@Controller
public class LoginAction {
	@Autowired
//	@Resource(name="userService")
	private UserService userService;
	
	private Map<String, Object> Rows;
	private String SUCCESS = "success";
	
	public String login(){
		String userName =  ServletActionContext.getRequest().getParameter("userName");
		String pass =  ServletActionContext.getRequest().getParameter("pass");
//		String phone =  ServletActionContext.getRequest().getParameter("phone");
//		String email =  ServletActionContext.getRequest().getParameter("email");
		Map<String, Object> map = new HashMap<String, Object>();
		if (userName==null||pass==null||userName.isEmpty()||pass.isEmpty()) {
			map.put("resultCode", 501);
			map.put("message", "");
			setRows(map);
			return SUCCESS;
		}
		try{
			User user = userService.getUser(userName, pass);
			if (user != null) {
				map.put("resultCode", 200);
				map.put("message", "");
				map.put("date", user);
			} else {
				map.put("resultCode", "500");
				map.put("message", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", "503");
			map.put("message", "");
		}
		setRows(map);
		return SUCCESS;
	}
	
	public  String showUserList(){
		String userId =  ServletActionContext.getRequest().getParameter("userId");
		Map<String, Object> map = new HashMap<String, Object>();
		
		try{
			List<User> users = userService.getUsers(userId);
			if (users.size() > 0) {
				map.put("users", users);
				map.put("resultCode", "200");
			} else {
				map.put("resultCode", "519");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", "503");
		}
//		setRows(map);
		return SUCCESS;
	}
	
	public Map<String, Object> getRows() {
		return Rows;
	}

	public void setRows(Map<String, Object> rows) {
		Rows = rows;
	}
	

}
