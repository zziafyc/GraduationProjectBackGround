package com.zzia.graduation.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PhotoConnect implements Serializable {

	/**
	 * 图片关联表
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int id;
	
	private String diaryId;
	
	private int trendId;
	
	private String photoSite;
	
	private String photoThumbSite;
	
	private int connectType;

	public PhotoConnect() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(String diaryId) {
		this.diaryId = diaryId;
	}

	public int getTrendId() {
		return trendId;
	}

	public void setTrendId(int trendId) {
		this.trendId = trendId;
	}

	public String getPhotoSite() {
		return photoSite;
	}

	public void setPhotoSite(String photoSite) {
		this.photoSite = photoSite;
	}

	public String getPhotoThumbSite() {
		return photoThumbSite;
	}

	public void setPhotoThumbSite(String photoThumbSite) {
		this.photoThumbSite = photoThumbSite;
	}

	public int getConnectType() {
		return connectType;
	}

	public void setConnectType(int connectType) {
		this.connectType = connectType;
	}
	
	
	

}
