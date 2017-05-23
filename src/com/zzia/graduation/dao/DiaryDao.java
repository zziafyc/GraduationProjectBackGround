package com.zzia.graduation.dao;

import java.util.List;

import com.zzia.graduation.base.BaseDao;
import com.zzia.graduation.model.Diary;

public interface DiaryDao extends BaseDao<Diary>{
	//获取好友发布的日记
	public List<Diary> getAllFriendsDiary(List<String> userIds,String orderName,int currentPage,int count);

}
