package com.zzia.graduation.daoBean;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.zzia.graduation.base.BaseDaoBean;
import com.zzia.graduation.dao.DiaryDao;
import com.zzia.graduation.model.Diary;

@Repository
public class DiaryDaoBean extends BaseDaoBean<Diary> implements DiaryDao {

	@Override
	public List<Diary> getAllFriendsDiary(List<String> userIds,String orderName,int currentPage,int count) {
		Query query = getSession().createQuery("from " + " Diary"  + " where userId in (:alist) "+" order by " + orderName + " desc");
		query.setParameterList("alist", userIds); 
		query.setFirstResult(count * (currentPage - 1));// 设置起始行
		query.setMaxResults(count);// 每页条数
		List<Diary> list = query.list();
		if (list != null && !list.isEmpty()) {
			return list;
		} else {
			return null;
		}
	
	}

}
