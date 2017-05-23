package com.zzia.graduation.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzia.graduation.dao.PhotoConnectDao;
import com.zzia.graduation.dao.TravelMemberDao;
import com.zzia.graduation.dao.TravelPlanDao;
import com.zzia.graduation.dao.TravelRouteDao;
import com.zzia.graduation.dao.UserDao;
import com.zzia.graduation.model.PhotoConnect;
import com.zzia.graduation.model.TravelMember;
import com.zzia.graduation.model.TravelPlan;
import com.zzia.graduation.model.TravelRoute;
import com.zzia.graduation.model.User;
import com.zzia.graduation.service.TravelService;
import com.zzia.graduation.utils.DateUtils;
import com.zzia.graduation.utils.StringUtils;

@Service
public class TravelServiceImpl implements TravelService {
	@Autowired
	TravelPlanDao travelPlanDao;
	@Autowired
	PhotoConnectDao photoConnectDao;
	@Autowired
	UserDao userDao;
	@Autowired
	TravelMemberDao travelMemberDao;
	@Autowired
	TravelRouteDao travelRouteDao;

	@Override
	public List<TravelPlan> getTravelListByType(String createId, int type, String orderName, int currentPage,
			int count) {
		return travelPlanDao.queryAllPageDesc("createId", createId, "type", type, orderName, currentPage, count);
	}

	@Override
	public TravelPlan getTravelPlanDetail(String travelId) {
		TravelPlan travelPlan = travelPlanDao.queryOne("travelId", travelId);
		if (travelPlan != null) {
			User user = userDao.queryOne("userId", travelPlan.getCreateId());
			if (user != null) {
				travelPlan.setCreateUser(user);
			}
			List<PhotoConnect> photoConnects = photoConnectDao.queryAll("travelId", travelId);
			if (photoConnects != null && photoConnects.size() > 0) {
				travelPlan.setTravelPhotos(photoConnects);
			}
			List<TravelMember> members = travelMemberDao.queryAll("travelId", travelId, "state", 1);
			if (members != null && members.size() > 0) {
				for (TravelMember model : members) {
					User user2 = userDao.queryOne("userId", model.getMemberId());
					if (user2 != null) {
						model.setUser(user2);
					}
				}
				travelPlan.setMembers(members);

			}
			List<TravelRoute> routes = travelRouteDao.queryAllAsc("travelId", travelId, "startDate");
			if (routes != null && routes.size() > 0) {
				travelPlan.setTravelRoutes(routes);
			}
			return travelPlan;
		} else {
			return null;
		}
	}

	@Override
	public String createTravelPlan(TravelPlan travelPlan) {
		if (travelPlan != null) {
			if (StringUtils.isEmpty(travelPlan.getTravelId())) {
				travelPlan.setTravelId(StringUtils.getGUID());
			}
			travelPlan.setCreateId(travelPlan.getCreateUser().getUserId());
			travelPlan.setCreateDate(DateUtils.getDataTime());
			travelPlanDao.add(travelPlan);
			// 添加旅行路线
			List<TravelRoute> travelRoutes = travelPlan.getTravelRoutes();
			if (travelRoutes != null && travelRoutes.size() > 0) {
				for (TravelRoute model : travelRoutes) {
					model.setTravelId(travelPlan.getTravelId());
					model.setRouteId(StringUtils.getGUID());
					travelRouteDao.add(model);
				}

			}
			return travelPlan.getTravelId();

		}

		return null;
	}

	@Override
	public boolean updateTravelPlan(TravelPlan travelPlan) {
		if (travelPlan != null) {
			String travelId = travelPlan.getTravelId();
			travelPlanDao.delete("travelId", travelId);
			photoConnectDao.delete("travelId", travelId);
			travelRouteDao.delete("travelId", travelId);
			travelMemberDao.delete("travelId", travelId);
			createTravelPlan(travelPlan);
			return true;

		}
		return false;
	}

}
