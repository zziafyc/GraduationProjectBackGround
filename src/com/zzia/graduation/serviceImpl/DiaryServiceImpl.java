package com.zzia.graduation.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzia.graduation.dao.DiaryCommentDao;
import com.zzia.graduation.dao.DiaryDao;
import com.zzia.graduation.dao.DiaryPraiseDao;
import com.zzia.graduation.dao.PhotoConnectDao;
import com.zzia.graduation.dao.UserDao;
import com.zzia.graduation.dao.VideoConnectDao;
import com.zzia.graduation.model.Diary;
import com.zzia.graduation.model.DiaryComment;
import com.zzia.graduation.model.DiaryPraise;
import com.zzia.graduation.model.PhotoConnect;
import com.zzia.graduation.model.User;
import com.zzia.graduation.model.VideoConnect;
import com.zzia.graduation.service.DiaryService;
import com.zzia.graduation.utils.DateUtils;
import com.zzia.graduation.utils.StringUtils;

@Service
public class DiaryServiceImpl implements DiaryService {

	@Autowired
	DiaryDao diaryDao;
	@Autowired
	DiaryPraiseDao diaryPraiseDao;
	@Autowired
	DiaryCommentDao diaryCommentDao;
	@Autowired
	PhotoConnectDao photoConnectDao;
	@Autowired
	VideoConnectDao videoConnectDao;
	@Autowired
	UserDao userDao;

	@Override
	public List<Diary> getAllDiary(String orderName, String userId) {
		List<Diary> diaryList = diaryDao.queryAllPageDesc(orderName);
		if (diaryList != null && diaryList.size() > 0) {
			for (Diary diary : diaryList) {
				// 设置user信息
				User user = userDao.queryOne("userId", diary.getUserId());
				if (user != null) {
					diary.setUser(user);
				}
				// 设置点赞
				List<DiaryPraise> praises = diaryPraiseDao.queryAll("diaryId", diary.getDiaryId());
				if (praises != null && praises.size() > 0) {
					// 先设置点赞数、后判断该用户是否对该日记点赞
					diary.setPraiseCount(praises.size());
					for (DiaryPraise model : praises) {
						if (model.getUserId().equals(userId)) {
							diary.setHavePraise(true);
							break;
						}
					}

				}
				// 设置评论
				List<DiaryComment> comments = diaryCommentDao.queryAll("diaryId", diary.getDiaryId());
				if (comments != null && comments.size() > 0) {
					diary.setCommentCount(comments.size());
				}
				// 设置图片信息
				List<PhotoConnect> photoList = photoConnectDao.queryAll("diaryId", diary.getDiaryId());
				if (photoList != null && photoList.size() > 0) {
					diary.setPhotoList(photoList);
				}
				//设置视频信息
				VideoConnect videoConnect=videoConnectDao.queryOne("diaryId", diary.getDiaryId());
				if(videoConnect!=null){
					diary.setVideoConnect(videoConnect);
				}
			}

			return diaryList;
		}

		return null;
	}

	@Override
	public boolean addDiary(Diary diary) {
		if (diary != null) {
			diary.setDiaryId(StringUtils.getGUID());
			diary.setCreateDate(DateUtils.getDataTime());
			diaryDao.add(diary);
			List<PhotoConnect> photos = diary.getPhotoList();
			if (photos != null && photos.size() > 0) {
				for (PhotoConnect model : photos) {
					model.setDiaryId(diary.getDiaryId());
					photoConnectDao.add(model);
				}
			}
			VideoConnect videoConnect=diary.getVideoConnect();
			if(videoConnect!=null){
				videoConnect.setDiaryId(diary.getDiaryId());
				videoConnectDao.add(videoConnect);
				
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteDiary(String diaryId) {
		if (!StringUtils.isEmpty(diaryId)) {
			diaryDao.delete(diaryId);
			// 删除所有点赞信息
			List<DiaryPraise> praises = diaryPraiseDao.queryAll("diaryId", diaryId);
			if (praises != null && praises.size() > 0) {
				for (DiaryPraise model : praises) {
					diaryPraiseDao.delete(model.getId());
				}
			}
			// 删除所有评论信息
			List<DiaryComment> comments = diaryCommentDao.queryAll("diaryId", diaryId);
			if (comments != null && comments.size() > 0) {
				for (DiaryComment model : comments) {
					diaryCommentDao.delete(model.getId());

				}
			}
			// 删除图片信息
			List<PhotoConnect> photoConnects = photoConnectDao.queryAll("diaryId", diaryId);
			if (photoConnects != null && photoConnects.size() > 0) {
				for (PhotoConnect model : photoConnects) {
					photoConnectDao.delete(model.getId());

				}

			}
			// 删除视频信息
			List<VideoConnect> videoConnects = videoConnectDao.queryAll("diaryId", diaryId);
			if (videoConnects != null && videoConnects.size() > 0) {
				for (VideoConnect model : videoConnects) {
					videoConnectDao.delete(model.getId());

				}

			}
			return true;
		}
		return false;
	}

	@Override
	public boolean setPraiseState(DiaryPraise diaryPraise) {
		if (diaryPraise != null) {
			DiaryPraise praise = diaryPraiseDao.queryOne("userId", diaryPraise.getUserId(), "diaryId",
					diaryPraise.getDiaryId());
			if (praise != null) {
				diaryPraiseDao.delete(praise.getId());
				return true;

			} else {
				diaryPraiseDao.add(diaryPraise);
				return true;
			}
		}
		return false;
	}

}
