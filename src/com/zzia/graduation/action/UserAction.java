package com.zzia.graduation.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.zzia.graduation.base.BaseAction;
import com.zzia.graduation.base.Constants;
import com.zzia.graduation.model.User;
import com.zzia.graduation.service.UserService;
import com.zzia.graduation.utils.DateUtils;
import com.zzia.graduation.utils.ParameterUtils;
import com.zzia.graduation.utils.PutUtils;
import com.zzia.graduation.utils.StringUtils;

@Controller
public class UserAction extends BaseAction {
	@Autowired
	private UserService userService;

	// 获取所有的用户信息

	public String getAllUser() {
		try {
			List<User> list = userService.getAllUser();
			if (list != null) {
				setRows(PutUtils.success(list));
			} else {
				setRows(PutUtils.empty());
			}

		} catch (Exception e) {
			System.out.println(StringUtils.getErrorMsg());
			e.printStackTrace();
		}
		return SUCCESS;

	}

	// 获取某一个用户的信息，这里分为两种get、post方便测试
	// post请求
	public String getUserInfo() {
		try {
			JSONObject jsonObject = ParameterUtils.getObject(ServletActionContext.getRequest());
			if (ParameterUtils.judgeJsonParams(jsonObject, "userId")) {
				String userId = jsonObject.getString("userId");
				User user = userService.getUser("userId", userId);
				if (user != null) {
					setRows(PutUtils.success(user));
				} else {
					setRows(PutUtils.empty("该用户不存在！"));
				}

			} else {
				setRows(PutUtils.parameterError());
			}
		} catch (Exception e) {
			System.out.println(StringUtils.getErrorMsg());
			e.printStackTrace();
		}

		return SUCCESS;
	}

	// get请求
	public String getUserInfo2() {
		try {
			String userId = ServletActionContext.getRequest().getParameter("userId");
			if (userId != null) {
				User user = userService.getUser("userId", userId);
				if (user != null) {
					setRows(PutUtils.success(user));
				} else {
					setRows(PutUtils.empty("该用户不存在！"));
				}

			} else {
				setRows(PutUtils.parameterError());
			}
		} catch (Exception e) {
			System.out.println(StringUtils.getErrorMsg());
			e.printStackTrace();
		}

		return SUCCESS;
	}

	// 用户、对于已经注册的的微信、qq则返回成功后直接走登录
	public String register() {
		try {

			User user = (User) ParameterUtils.getObject(ServletActionContext.getRequest(), User.class);
			if (user != null) {
				// 手机号
				if (!StringUtils.isEmpty(user.getTel())) {
					if (userService.getUser("tel", user.getTel()) != null) {
						setRows(PutUtils.success(Constants.Code.EXIST, "对不起，该用户手机号已经注册"));
					} else {
						User user2 = userService.register(user);
						if (user2 != null) {
							setRows(PutUtils.success(user2));
						} else {
							setRows(PutUtils.success(Constants.Code.ERROR, "获取token失败"));

						}
					}
				} else if (!StringUtils.isEmpty(user.getWeChat())) {
					// 微信
					if (userService.getUser("weChat", user.getWeChat()) != null) {
						setRows(PutUtils.success(Constants.Code.EXIST, "该用户微信已经注册"));
					} else {
						User user2 = userService.register(user);
						if (user2 != null) {
							setRows(PutUtils.success(userService.register(user)));
						} else {
							setRows(PutUtils.success(Constants.Code.ERROR, "获取token失败"));

						}
					}
				} else if (!StringUtils.isEmpty(user.getQq())) {
					// qq
					if (userService.getUser("qq", user.getQq()) != null) {
						setRows(PutUtils.success(Constants.Code.EXIST, "该用户qq已经注册"));
					} else {
						User user2 = userService.register(user);
						if (user2 != null) {
							setRows(PutUtils.success(user2));
						} else {
							setRows(PutUtils.success(Constants.Code.ERROR, "获取token失败"));

						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			setRows(PutUtils.error());
		}
		return SUCCESS;
	}

	// 用户登录，这个只是针对用户手机号密码的
	public String login() {

		try {
			User user = (User) ParameterUtils.getObject(ServletActionContext.getRequest(), User.class);
			if (user != null) {
				if (ParameterUtils.judgeParams(user.getTel(), user.getPassword())) {
					User user2 = userService.getUser("tel", user.getTel(), "password", user.getPassword());
					if (user2 != null) {
						if(user2.getState()==0){
							//更新用户的登录状态
							user2.setState(1);
							user2.setLoginDate(DateUtils.getDataTime());
							userService.updateUser("userId", user2.getUserId(), "state", 1);
							userService.updateUser("userId", user2.getUserId(), "loginDate",DateUtils.getDataTime());
							setRows(PutUtils.success(user2, "登录成功"));
						}else{
							setRows(PutUtils.success(Constants.Code.ONLINE, "用户已在其他终端登录"));
						}
						
					} else {
						setRows(PutUtils.success(Constants.Code.ERROR, "用户名或密码错误！"));
					}
				} else {
					setRows(PutUtils.error(Constants.Code.PARAMSERROR, "参数错误"));
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			setRows(PutUtils.error());
		}

		return SUCCESS;
	}

}
