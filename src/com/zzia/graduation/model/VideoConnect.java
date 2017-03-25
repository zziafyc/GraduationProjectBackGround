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
	
	private int trendId;
	
	private String VideoSite;
	
	private String VideoCoverSite;
	
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

	public int getTrendId() {
		return trendId;
	}

	public void setTrendId(int trendId) {
		this.trendId = trendId;
	}

	public String getVideoSite() {
		return VideoSite;
	}

	public void setVideoSite(String videoSite) {
		VideoSite = videoSite;
	}

	public String getVideoCoverSite() {
		return VideoCoverSite;
	}

	public void setVideoCoverSite(String videoCoverSite) {
		VideoCoverSite = videoCoverSite;
	}

	public String getVideoTime() {
		return videoTime;
	}

	public void setVideoTime(String videoTime) {
		this.videoTime = videoTime;
	}
	
	

}
