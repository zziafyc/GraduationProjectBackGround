package com.zzia.graduation.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.zzia.graduation.base.BaseAction;
import com.zzia.graduation.base.Constants;
import com.zzia.graduation.model.Diary;
import com.zzia.graduation.model.TravelPlan;
import com.zzia.graduation.service.TravelService;
import com.zzia.graduation.utils.ParameterUtils;
import com.zzia.graduation.utils.PutUtils;
import com.zzia.graduation.utils.StringUtils;

@Controller
public class TravelAction extends BaseAction {
	@Autowired
	TravelService travelService;

	// 得到所有旅行计划
	public String getTravelListByType() {
		try {
			String createId = ServletActionContext.getRequest().getParameter("createId");
			int type = Integer.parseInt(ServletActionContext.getRequest().getParameter("type"));
			int currentPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currentPage"));
			int count = Integer.parseInt(ServletActionContext.getRequest().getParameter("count"));
			List<TravelPlan> list = travelService.getTravelListByType(createId, type, "createDate", currentPage, count);
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

	// 得到某一个旅行的详细信息
	public String getTravelPlanDetail() {
		try {
			String travelId = ServletActionContext.getRequest().getParameter("travelId");
			if (ParameterUtils.judgeParams(travelId)) {
				TravelPlan travelPlan = travelService.getTravelPlanDetail(travelId);
				if (travelPlan != null) {
					setRows(PutUtils.success(travelPlan));
				} else {
					setRows(PutUtils.empty());
				}
			} else {
				// 参数
				setRows(PutUtils.error(Constants.Code.PARAMSERROR, "参数不完整或错误"));

			}

		} catch (Exception e) {
			System.out.println(StringUtils.getErrorMsg());
			e.printStackTrace();
		}
		return SUCCESS;

	}

	// 添加旅行计划
	public String createTravelPlan() {
		try {
			TravelPlan travelPlan = (TravelPlan) ParameterUtils.getObject(ServletActionContext.getRequest(),
					TravelPlan.class);

			if (travelPlan != null) {
				if (StringUtils.isEmpty(travelPlan.getTravelId())) {
					String travelId = travelService.createTravelPlan(travelPlan);
					if (!StringUtils.isEmpty(travelId)) {
						setRows(PutUtils.success(travelId));
					} else {
						PutUtils.error(Constants.Code.ERROR, "添加旅行计划失败！");
					}
				}else{
					//修改操作了
					if(travelService.updateTravelPlan(travelPlan)){
						setRows(PutUtils.success());
					}else{
						PutUtils.error(Constants.Code.ERROR, "修改旅行计划失败！");

					}
				}
			} else {
				// 参数
				setRows(PutUtils.error(Constants.Code.PARAMSERROR, "参数不完整或错误"));

			}

		} catch (Exception e) {
			System.out.println(StringUtils.getErrorMsg());
			e.printStackTrace();
		}
		return SUCCESS;

	}

}
