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

	private String createDate;

	private int type; // 旅行类型：0单身游 1情侣游 2组团游

	private String travelThemePic;
	@Transient
	private User createUser;
	@Transient
	private List<TravelRoute> travelRoutes;
	@Transient
	private List<PhotoConnect> travelPhotos;
	@Transient
	private List<TravelMember> members;
	

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

	public String getTravelThemePic() {
		return travelThemePic;
	}

	public void setTravelThemePic(String travelThemePic) {
		this.travelThemePic = travelThemePic;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public List<TravelRoute> getTravelRoutes() {
		return travelRoutes;
	}

	public void setTravelRoutes(List<TravelRoute> travelRoutes) {
		this.travelRoutes = travelRoutes;
	}

	public List<PhotoConnect> getTravelPhotos() {
		return travelPhotos;
	}

	public void setTravelPhotos(List<PhotoConnect> travelPhotos) {
		this.travelPhotos = travelPhotos;
	}

	public List<TravelMember> getMembers() {
		return members;
	}

	public void setMembers(List<TravelMember> members) {
		this.members = members;
	}


	
}
