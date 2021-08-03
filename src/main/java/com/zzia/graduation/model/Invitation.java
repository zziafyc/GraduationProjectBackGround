package com.zzia.graduation.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Invitation implements Serializable {

	/**
	 * 邀请表
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int id;
	
    private String travelId;
	
	private String createId;
	
	private String memberId;
	
	private String inviteDes;
	
	private String inviteDate;
	
	private int state;

	public Invitation() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getInviteDes() {
		return inviteDes;
	}

	public void setInviteDes(String inviteDes) {
		this.inviteDes = inviteDes;
	}

	public String getInviteDate() {
		return inviteDate;
	}

	public void setInviteDate(String inviteDate) {
		this.inviteDate = inviteDate;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	
	
	

}
