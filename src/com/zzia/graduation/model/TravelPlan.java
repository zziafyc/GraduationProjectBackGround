package com.zzia.graduation.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class TravelPlan implements Serializable {

	/**
	 * 旅行计划类
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name = "systemUUID", strategy = "assigned")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "travelId", insertable = true, updatable = true, nullable = false)

	
	private String travelId;
	
	private String createId;
	
	private String travelTheme;
	
	private String travelDes;
	
	private String startDate;
	
	private String endDate;

	public TravelPlan() {
		super();
	}

	public String getTravelId() {
		return travelId;
	}

	public void setTravelId(String travelId) {
		this.travelId = travelId;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public String getTravelTheme() {
		return travelTheme;
	}

	public void setTravelTheme(String travelTheme) {
		this.travelTheme = travelTheme;
	}

	public String getTravelDes() {
		return travelDes;
	}

	public void setTravelDes(String travelDes) {
		this.travelDes = travelDes;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
	

}
