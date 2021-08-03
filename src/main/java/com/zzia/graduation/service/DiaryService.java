package com.zzia.graduation.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zzia.graduation.model.Diary;
import com.zzia.graduation.model.DiaryPraise;

@Transactional
public interface DiaryService {
	//得到所有的校园日记,需按照时间顺序排列
	public List<Diary> getAllDiary(String orderName,String userId,int currentPage,int count);
	//得到所有我的好友的校园日记,需按照时间顺序排列
	public List<Diary> getAllFriendsDiary(String orderName,String userId, int currentPage,int count);
	//得到所有我发布校园日记,需按照时间顺序排列
	public List<Diary> getAllMyDiary(String orderName,String userId, int currentPage,int count);
	//添加一条日记记录
	public boolean addDiary(Diary diary);
	//删除一条记录
	public boolean deleteDiary(String diaryId);
	//点赞某一条日记，取消也走这个方法
	public boolean setPraiseState(DiaryPraise diaryPraise);

	
}
