package com.SupplyChainManagementProject.Model;

public class UserUserType {
	private int userUserTypeId;
	private int userId;
	private int userTypeId;
	public UserUserType() {
		super();
	}
	public UserUserType(int userUserTypeId, int userId, int userTypeId) {
		super();
		this.userUserTypeId = userUserTypeId;
		this.userId = userId;
		this.userTypeId = userTypeId;
	}
	public int getUserUserTypeId() {
		return userUserTypeId;
	}
	public void setUserUserTypeId(int userUserTypeId) {
		this.userUserTypeId = userUserTypeId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}

}
