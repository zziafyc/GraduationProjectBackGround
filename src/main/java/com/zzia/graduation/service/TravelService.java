package com.zzia.graduation.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zzia.graduation.model.TravelPlan;

@Transactional
public interface TravelService {
	//得到某一种类型的所有记录
	public List<TravelPlan> getTravelListByType(String userId,int type,String orderName,int currentPage,int count);

	//获取某一个旅行的详细信息
	public TravelPlan getTravelPlanDetail(String travelId);
	
	//创建旅行计划,返回日记的id，方便添加邀请函
	public String createTravelPlan(TravelPlan travelPlan);
	
	//修改travelPlan操作
	public boolean updateTravelPlan(TravelPlan travelPlan);
}
