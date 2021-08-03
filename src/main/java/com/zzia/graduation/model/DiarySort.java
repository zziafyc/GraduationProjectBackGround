package com.zzia.graduation.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class DiarySort implements Serializable {

	/**
	 * 日记分类表
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name = "systemUUID", strategy = "assigned")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "sortId", insertable = true, updatable = true, nullable = false)
	
	private String sortId;
	
	private String userId;
	
	private String sortName;

	public DiarySort() {
		super();
	}

	public String getSortId() {
		return sortId;
	}

	public void setSortId(String sortId) {
		this.sortId = sortId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	
	

}
