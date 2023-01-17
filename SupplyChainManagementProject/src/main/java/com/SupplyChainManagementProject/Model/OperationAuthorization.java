package com.SupplyChainManagementProject.Model;

import com.SupplyChainManagementProject.Core.Model.GenericModel;

public class OperationAuthorization implements GenericModel<OperationAuthorization>{
	private int operationAuthorizationId;
	private String definition;
	public OperationAuthorization() {
		super();
	}
	public OperationAuthorization(int operationAuthorizationId, String definition) {
		super();
		this.operationAuthorizationId = operationAuthorizationId;
		this.definition = definition;
	}
	public int getOperationAuthorizationId() {
		return operationAuthorizationId;
	}
	public void setOperationAuthorizationId(int operationAuthorizationId) {
		this.operationAuthorizationId = operationAuthorizationId;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}

}
