package com.zzia.graduation.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class TravelRoute implements Serializable {

	/**
	 * 旅行路线类
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name = "systemUUID", strategy = "assigned")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "routeId", insertable = true, updatable = true, nullable = false)

	
	private String routeId;
	
    private String travelId;
	
	private String routeDes;
	
	private String startDate;
	
	private String endDate;

	public TravelRoute() {
		super();
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getTravelId() {
		return travelId;
	}

	public void setTravelId(String travelId) {
		this.travelId = travelId;
	}

	public String getRouteDes() {
		return routeDes;
	}

	public void setRouteDes(String routeDes) {
		this.routeDes = routeDes;
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
