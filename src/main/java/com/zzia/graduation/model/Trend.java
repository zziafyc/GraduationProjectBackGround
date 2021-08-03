package com.zzia.graduation.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Trend implements Serializable {

	/**
	 * 动态表
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name = "systemUUID", strategy = "assigned")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "trendId", insertable = true, updatable = true, nullable = false)

	
	private String trendId;
	
	private String userId;
	
	private String trendTitle;
	
	private String trendContent;
	
	private String createDate;
	
	private String trendLocation;
	
	private String authority;

	public Trend() {
		super();
	}

	public String getTrendId() {
		return trendId;
	}

	public void setTrendId(String trendId) {
		this.trendId = trendId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTrendTitle() {
		return trendTitle;
	}

	public void setTrendTitle(String trendTitle) {
		this.trendTitle = trendTitle;
	}

	public String getTrendContent() {
		return trendContent;
	}

	public void setTrendContent(String trendContent) {
		this.trendContent = trendContent;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getTrendLocation() {
		return trendLocation;
	}

	public void setTrendLocation(String trendLocation) {
		this.trendLocation = trendLocation;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	

}
