package com.zzia.graduation.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DiaryReward implements Serializable {

	/**
	 * 日记打赏表
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int id;
	
	private String diaryId;
	
	private String userId;
	
	private String rewardMoney;
	
	private String rewardDes;
	
	private String rewardDate;

	public DiaryReward() {
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRewardMoney() {
		return rewardMoney;
	}

	public void setRewardMoney(String rewardMoney) {
		this.rewardMoney = rewardMoney;
	}

	public String getRewardDes() {
		return rewardDes;
	}

	public void setRewardDes(String rewardDes) {
		this.rewardDes = rewardDes;
	}

	public String getRewardDate() {
		return rewardDate;
	}

	public void setRewardDate(String rewardDate) {
		this.rewardDate = rewardDate;
	}
	
	

}
