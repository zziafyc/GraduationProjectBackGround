package com.zzia.graduation.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
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

@Controller
public class DiaryAction extends BaseAction {
	@Autowired
	DiaryService diaryService;


	// 得到所有日记
	public String getAllDiary() {
		try {
			String usreId=ServletActionContext.getRequest().getParameter("userId");
			int currentPage=Integer.parseInt(ServletActionContext.getRequest().getParameter("currentPage"));
			int count=Integer.parseInt(ServletActionContext.getRequest().getParameter("count"));
			List<Diary> list = diaryService.getAllDiary("createDate",usreId,currentPage,count);
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
		public String getAllFriendsDiary() {
			try {
				String usreId=ServletActionContext.getRequest().getParameter("userId");
				int currentPage=Integer.parseInt(ServletActionContext.getRequest().getParameter("currentPage"));
				int count=Integer.parseInt(ServletActionContext.getRequest().getParameter("count"));
				List<Diary> list = diaryService.getAllFriendsDiary("createDate",usreId,currentPage,count);
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
		public String getAllMyDiary() {
			try {
				String usreId=ServletActionContext.getRequest().getParameter("userId");
				int currentPage=Integer.parseInt(ServletActionContext.getRequest().getParameter("currentPage"));
				int count=Integer.parseInt(ServletActionContext.getRequest().getParameter("count"));
				List<Diary> list = diaryService.getAllMyDiary("createDate",usreId,currentPage,count);
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
	public String addDiary() {
		try {
			Diary diary = (Diary) ParameterUtils.getObject(ServletActionContext.getRequest(), Diary.class);
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
	public String deleteDiary() {
		try {
			String diaryId = ServletActionContext.getRequest().getParameter("diaryId");
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
	public String setPraiseState() {
		try {
			DiaryPraise diaryPraise=(DiaryPraise) ParameterUtils.getObject(ServletActionContext.getRequest(),DiaryPraise.class);
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
