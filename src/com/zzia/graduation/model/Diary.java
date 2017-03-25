package com.zzia.graduation.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	
	private String createDate;
	
	private int authority;
	
	private String sortId;
	
	private int state;

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
	
	

}
