package com.SupplyChainManagementProject.Model;

public class UserType {
	private int userTypeId;
	private String definition;
	public UserType() {
		super();
	}
	public UserType(int userTypeId, String definition) {
		super();
		this.userTypeId = userTypeId;
		this.definition = definition;
	}
	public int getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}

}
