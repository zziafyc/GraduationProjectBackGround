package com.zzia.graduation.action;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zzia.graduation.base.BaseAction;
import com.zzia.graduation.base.Constants;
import com.zzia.graduation.model.Friends;
import com.zzia.graduation.model.User;
import com.zzia.graduation.service.UserService;
import com.zzia.graduation.utils.DateUtils;
import com.zzia.graduation.utils.ParameterUtils;
import com.zzia.graduation.utils.PutUtils;
import com.zzia.graduation.utils.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserAction extends BaseAction {
    @Autowired
    private UserService userService;

    // 获取所有的用户信息

    @GetMapping("userAction_getAllUser")
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
    @RequestMapping(value = "userAction_getUserInfo", method = {RequestMethod.GET, RequestMethod.POST})
    public String getUserInfo(String userId) {
        try {
            if (ParameterUtils.judgeParams(userId)) {
                User user = userService.getUser("userId", userId);
                if (user != null) {
                    setRows(PutUtils.success(user));
                } else {
                    setRows(PutUtils.empty("该用户已不存在！"));
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

    // 修改用户图像
    @RequestMapping(value = "userAction_updateUserAvatar", method = {RequestMethod.GET, RequestMethod.POST})
    public String updateUserAvatar(@RequestBody User user) {

        try {
            if (user != null) {
                if (userService.updateUser("userId", user.getUserId(), "avatar", user.getAvatar()) > 0) {
                    setRows(PutUtils.success());
                } else {
                    PutUtils.error(Constants.Code.ERROR, "申请发送失败！");
                }
            } else {
                setRows(PutUtils.error(Constants.Code.PARAMSERROR, "参数不完整或错误"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            setRows(PutUtils.error());
        }

        return SUCCESS;
    }

    // 修改登录状态
    @RequestMapping(value = "userAction_updateLoginState", method = {RequestMethod.GET, RequestMethod.POST})
    public String updateLoginState(String userId) {
        try {
            if (ParameterUtils.judgeParams(userId)) {
                User user = userService.getUser("userId", userId);
                if (user != null) {
                    if (userService.updateLoginState(user)) {
                        setRows(PutUtils.success());
                    } else {
                        PutUtils.error(Constants.Code.ERROR, "修改状态失败！");
                    }
                } else {
                    setRows(PutUtils.empty("该用户已不存在！"));
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
    @RequestMapping(value = "userAction_register", method = {RequestMethod.GET, RequestMethod.POST})
    public String register(@RequestBody User user) {
        try {
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
    @RequestMapping(value = "userAction_login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login(@RequestBody User user) {

        try {
            if (user != null) {
                if (ParameterUtils.judgeParams(user.getTel(), user.getPassword())) {
                    User user2 = userService.getUser("tel", user.getTel(), "password", user.getPassword());
                    if (user2 != null) {
                        if (user2.getState() == 0) {
                            // 更新用户的登录状态
                            user2.setState(1);
                            user2.setLoginDate(DateUtils.getDataTime());
                            userService.updateUser("userId", user2.getUserId(), "state", 1);
                            userService.updateUser("userId", user2.getUserId(), "loginDate", DateUtils.getDataTime());
                            setRows(PutUtils.success(user2, "登录成功"));
                        } else {
                            setRows(PutUtils.success(Constants.Code.ONLINE, "用户已在其他终端登录"));
                        }

                    } else {
                        setRows(PutUtils.success(Constants.Code.ERROR, "用户名或密码错误！"));
                    }
                } else {
                    setRows(PutUtils.error(Constants.Code.PARAMSERROR, "参数不完整或错误"));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            setRows(PutUtils.error());
        }

        return SUCCESS;
    }

    // 获取好友列表,带字母的map
    @RequestMapping(value = "userAction_getAllFriends", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllFriends(String userId) {

        try {
            if (ParameterUtils.judgeParams(userId)) {
                Map<String, List<Object>> map = userService.getAllFriends(userId);
                if (map != null) {
                    setRows(PutUtils.success(map, "返回好友列表成功"));
                } else {
                    setRows(PutUtils.empty("暂无好友哦"));
                }

            } else {
                setRows(PutUtils.error(Constants.Code.PARAMSERROR, "参数不完整或错误"));
            }

        } catch (Exception e) {
            setRows(PutUtils.error());
        }
        return SUCCESS;
    }

    // 获取好友列表,list集合
    @RequestMapping(value = "userAction_getAllFriendsList", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllFriendsList(String userId) {

        try {
            if (ParameterUtils.judgeParams(userId)) {
                List<User> list = userService.getAllFriendsList(userId);
                if (list != null) {
                    setRows(PutUtils.success(list, "返回好友列表成功"));
                } else {
                    setRows(PutUtils.empty("暂无好友哦"));
                }

            } else {
                setRows(PutUtils.error(Constants.Code.PARAMSERROR, "参数不完整或错误"));
            }

        } catch (Exception e) {
            setRows(PutUtils.error());
        }
        return SUCCESS;
    }

    @RequestMapping(value = "userAction_addFriend", method = {RequestMethod.GET, RequestMethod.POST})
    public String addFriend(@RequestBody Friends friend) {

        try {
            if (friend != null) {
                if (userService.addFriend(friend)) {
                    setRows(PutUtils.success());
                } else {
                    PutUtils.error(Constants.Code.ERROR, "申请发送失败！");
                }
            } else {
                setRows(PutUtils.error(Constants.Code.PARAMSERROR, "参数不完整或错误"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            setRows(PutUtils.error());
        }

        return SUCCESS;
    }

    // 模糊查询用户，用于加好友
    @RequestMapping(value = "userAction_searchUser", method = {RequestMethod.GET, RequestMethod.POST})
    public String searchUser(String key) {
        try {
            if (!StringUtils.isEmpty(key)) {
                List<User> list = userService.searchUser(key, "tel", "nickName");
                if (list != null && list.size() > 0) {
                    setRows(PutUtils.success(list));
                } else {
                    setRows(PutUtils.empty());
                }
            } else {
                setRows(PutUtils.error(Constants.Code.PARAMSERROR, "参数不完整或错误"));

            }

        } catch (Exception e) {
            System.out.println(StringUtils.getErrorMsg());
            e.printStackTrace();
        }
        return SUCCESS;

    }

    // 删除一条好友申请记录
    @RequestMapping(value = "userAction_deleteFriendMessage", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteFriendMessage(int id) {
        try {
            if (id > 0) {
                if (userService.deleteFriendMessage(id)) {
                    setRows(PutUtils.success());
                } else {
                    setRows(PutUtils.error());
                }
            } else {
                setRows(PutUtils.error(Constants.Code.PARAMSERROR, "参数不完整或错误"));

            }

        } catch (Exception e) {
            System.out.println(StringUtils.getErrorMsg());
            e.printStackTrace();
        }
        return SUCCESS;

    }

    // 得到所有好友申请记录
    @RequestMapping(value = "userAction_getAllFriendMessage", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllFriendMessage(String userId, int currentPage, int count) {
        try {

            if (!StringUtils.isEmpty(userId)) {
                List<Friends> friendsMessages = userService.getAllFriendMessage(userId, currentPage, count);
                if (friendsMessages != null && friendsMessages.size() > 0) {
                    setRows(PutUtils.success(friendsMessages));
                } else {
                    setRows(PutUtils.empty());
                }
            } else {
                setRows(PutUtils.error(Constants.Code.PARAMSERROR, "参数不完整或错误"));

            }

        } catch (Exception e) {
            System.out.println(StringUtils.getErrorMsg());
            e.printStackTrace();
        }
        return SUCCESS;

    }

    // 修改同意状态，完成加好友
    @RequestMapping(value = "userAction_changeMessageState", method = {RequestMethod.GET, RequestMethod.POST})
    public String changeMessageState(int id, String remark) {
        try {
            if (ParameterUtils.judgeParams(String.valueOf(id), remark)) {
                if (userService.changeMessageState(id, remark)) {
                    setRows(PutUtils.success());
                } else {
                    setRows(PutUtils.error());
                }
            } else {
                setRows(PutUtils.error(Constants.Code.PARAMSERROR, "参数不完整或错误"));

            }

        } catch (Exception e) {
            System.out.println(StringUtils.getErrorMsg());
            e.printStackTrace();
        }
        return SUCCESS;

    }

    // 修改同意状态，完成加好友
    @RequestMapping(value = "userAction_changeRemark", method = {RequestMethod.GET, RequestMethod.POST})
    public String changeRemark(int id, String remark) {
        try {
            if (ParameterUtils.judgeParams(String.valueOf(id), remark)) {
                if (userService.changeRemark(id, remark)) {
                    setRows(PutUtils.success());
                } else {
                    setRows(PutUtils.error());
                }
            } else {
                setRows(PutUtils.error(Constants.Code.PARAMSERROR, "参数不完整或错误"));

            }

        } catch (Exception e) {
            System.out.println(StringUtils.getErrorMsg());
            e.printStackTrace();
        }
        return SUCCESS;

    }
}