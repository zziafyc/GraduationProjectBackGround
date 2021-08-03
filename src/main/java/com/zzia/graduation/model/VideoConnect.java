package com.zzia.graduation.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VideoConnect implements Serializable {

	/**
	 * 视频关联表
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int id;
	
	private String trendId;
	
	private String diaryId;
	
	private String videoSite;
	
	private String videoCoverSite;
	
	private String videoTime;

	public VideoConnect() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public String getTrendId() {
		return trendId;
	}

	public void setTrendId(String trendId) {
		this.trendId = trendId;
	}

	public String getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(String diaryId) {
		this.diaryId = diaryId;
	}

	public String getVideoSite() {
		return videoSite;
	}

	public void setVideoSite(String videoSite) {
		this.videoSite = videoSite;
	}

	public String getVideoCoverSite() {
		return videoCoverSite;
	}

	public void setVideoCoverSite(String videoCoverSite) {
		this.videoCoverSite = videoCoverSite;
	}

	public String getVideoTime() {
		return videoTime;
	}

	public void setVideoTime(String videoTime) {
		this.videoTime = videoTime;
	}
	
	

}
