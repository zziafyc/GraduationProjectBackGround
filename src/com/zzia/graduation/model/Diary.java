package com.zzia.graduation.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Diary implements Serializable {

	/**
	 * 日记类
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "diaryId", strategy = "assigned")
	@GeneratedValue(generator = "diaryId")
	@Column(name = "diaryId", insertable = true, updatable = true, nullable = false)

	private String diaryId;

	private String userId;

	private String diaryTitle;

	private String diaryContent;

	public String createDate;

	private int authority;

	private String sortId;

	private int state;
	
	private String sendAddress;

	@Transient
	private User user;

	@Transient
	private List<PhotoConnect> photoList;

	@Transient
	private VideoConnect videoConnect;

	@Transient
	private String createDateTransfer;//转化成几天前的格式
	
	@Transient
	private int praiseCount; // 点赞数
	
	@Transient
	private int commentCount; // 评论数
	
	@Transient
	private boolean havePraise;  //是否点赞
	

	public Diary() {
		super();
	}

	public String getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(String diaryId) {
		this.diaryId = diaryId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDiaryTitle() {
		return diaryTitle;
	}

	public void setDiaryTitle(String diaryTitle) {
		this.diaryTitle = diaryTitle;
	}

	public String getDiaryContent() {
		return diaryContent;
	}

	public void setDiaryContent(String diaryContent) {
		this.diaryContent = diaryContent;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public String getSortId() {
		return sortId;
	}

	public void setSortId(String sortId) {
		this.sortId = sortId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<PhotoConnect> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(List<PhotoConnect> photoList) {
		this.photoList = photoList;
	}

	public VideoConnect getVideoConnect() {
		return videoConnect;
	}

	public void setVideoConnect(VideoConnect videoConnect) {
		this.videoConnect = videoConnect;
	}

	public String getCreateDateTransfer() {
		return createDateTransfer;
	}

	public void setCreateDateTransfer(String createDateTransfer) {
		this.createDateTransfer = createDateTransfer;
	}

	public int getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(int praiseCount) {
		this.praiseCount = praiseCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public boolean isHavePraise() {
		return havePraise;
	}

	public void setHavePraise(boolean havePraise) {
		this.havePraise = havePraise;
	}

	public String getSendAddress() {
		return sendAddress;
	}

	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}


}
