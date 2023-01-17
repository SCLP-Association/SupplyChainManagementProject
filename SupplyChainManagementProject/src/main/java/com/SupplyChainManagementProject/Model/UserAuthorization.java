package com.SupplyChainManagementProject.Model;

import com.SupplyChainManagementProject.Core.Model.GenericModel;

public class UserAuthorization implements GenericModel<UserAuthorization>{
	private int userAuthorizationId;
	private int operationAuthorizationId;
	private int userTypeId;
	public UserAuthorization() {
		super();
	}
	public UserAuthorization(int userAuthorizationId, int operationAuthorizationId, int userTypeId) {
		super();
		this.userAuthorizationId = userAuthorizationId;
		this.operationAuthorizationId = operationAuthorizationId;
		this.userTypeId = userTypeId;
	}
	public int getUserAuthorizationId() {
		return userAuthorizationId;
	}
	public void setUserAuthorizationId(int userAuthorizationId) {
		this.userAuthorizationId = userAuthorizationId;
	}
	public int getOperationAuthorizationId() {
		return operationAuthorizationId;
	}
	public void setOperationAuthorizationId(int operationAuthorizationId) {
		this.operationAuthorizationId = operationAuthorizationId;
	}
	public int getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}

}
