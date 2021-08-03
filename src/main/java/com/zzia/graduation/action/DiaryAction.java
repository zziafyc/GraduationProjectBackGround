package com.zzia.graduation.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.zzia.graduation.base.BaseAction;
import com.zzia.graduation.base.Constants;
import com.zzia.graduation.model.Diary;
import com.zzia.graduation.model.DiaryPraise;
import com.zzia.graduation.service.DiaryService;
import com.zzia.graduation.utils.ParameterUtils;
import com.zzia.graduation.utils.PutUtils;
import com.zzia.graduation.utils.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiaryAction extends BaseAction {
    @Autowired
    DiaryService diaryService;


    // 得到所有日记
    @RequestMapping(value = "diaryAction_getAllDiary", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllDiary(String usreId, int currentPage, int count) {
        try {

            List<Diary> list = diaryService.getAllDiary("createDate", usreId, currentPage, count);
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

    //得到好友的所有日记（包括自己的）
    // 得到所有日记
    @RequestMapping(value = "diaryAction_getAllFriendsDiary", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllFriendsDiary(String usreId, int currentPage, int count) {
        try {
            List<Diary> list = diaryService.getAllFriendsDiary("createDate", usreId, currentPage, count);
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

    // 得到所有日记
    @RequestMapping(value = "diaryAction_getAllMyDiary", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllMyDiary(String usreId, int currentPage, int count) {
        try {
            List<Diary> list = diaryService.getAllMyDiary("createDate", usreId, currentPage, count);
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

    // 添加日记
    @RequestMapping(value = "diaryAction_addDiary", method = {RequestMethod.GET, RequestMethod.POST})
    public String addDiary(@RequestBody Diary diary) {
        try {
            if (diaryService.addDiary(diary)) {
                setRows(PutUtils.success());
            } else {
                PutUtils.error(Constants.Code.ERROR, "添加日记失败！");
            }

        } catch (Exception e) {
            System.out.println(StringUtils.getErrorMsg());
            e.printStackTrace();
        }
        return SUCCESS;

    }

    // 删除日记
    @RequestMapping(value = "diaryAction_deleteDiary", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteDiary(String diaryId) {
        try {
            if (diaryService.deleteDiary(diaryId)) {
                setRows(PutUtils.success());
            } else {
                PutUtils.error(Constants.Code.ERROR, "删除日记失败！");
            }

        } catch (Exception e) {
            System.out.println(StringUtils.getErrorMsg());
            e.printStackTrace();
        }
        return SUCCESS;

    }

    //点赞日记
    @RequestMapping(value = "diaryAction_setPraiseState", method = {RequestMethod.GET, RequestMethod.POST})
    public String setPraiseState(@RequestBody DiaryPraise diaryPraise) {
        try {
            if (diaryService.setPraiseState(diaryPraise)) {
                setRows(PutUtils.success());
            } else {
                PutUtils.error(Constants.Code.ERROR, "操作失败！");
            }

        } catch (Exception e) {
            System.out.println(StringUtils.getErrorMsg());
            e.printStackTrace();
        }
        return SUCCESS;

    }

}
