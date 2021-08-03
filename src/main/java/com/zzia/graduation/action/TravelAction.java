package com.zzia.graduation.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zzia.graduation.base.BaseAction;
import com.zzia.graduation.base.Constants;
import com.zzia.graduation.model.TravelPlan;
import com.zzia.graduation.service.TravelService;
import com.zzia.graduation.utils.ParameterUtils;
import com.zzia.graduation.utils.PutUtils;
import com.zzia.graduation.utils.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TravelAction extends BaseAction {
    @Autowired
    TravelService travelService;

    // 得到所有旅行计划
    @RequestMapping(value = "travelAction_getTravelListByType", method = {RequestMethod.GET, RequestMethod.POST})
    public String getTravelListByType(String createId, int type, int currentPage, int count) {
        try {

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
    @RequestMapping(value = "travelAction_getTravelPlanDetail", method = {RequestMethod.GET, RequestMethod.POST})
    public String getTravelPlanDetail(String travelId) {
        try {
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
    @RequestMapping(value = "travelAction_createTravelPlan", method = {RequestMethod.GET, RequestMethod.POST})
    public String createTravelPlan(@RequestBody TravelPlan travelPlan) {
        try {
            if (travelPlan != null) {
                if (StringUtils.isEmpty(travelPlan.getTravelId())) {
                    String travelId = travelService.createTravelPlan(travelPlan);
                    if (!StringUtils.isEmpty(travelId)) {
                        setRows(PutUtils.success(travelId));
                    } else {
                        PutUtils.error(Constants.Code.ERROR, "添加旅行计划失败！");
                    }
                } else {
                    //修改操作了
                    if (travelService.updateTravelPlan(travelPlan)) {
                        setRows(PutUtils.success());
                    } else {
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
