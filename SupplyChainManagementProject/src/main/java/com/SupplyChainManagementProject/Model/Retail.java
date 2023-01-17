package com.SupplyChainManagementProject.Model;

import com.SupplyChainManagementProject.Core.Model.GenericModel;

public class Retail implements GenericModel<Retail>{
    private int retailId;
    private String retailInfo;
    private int userId;
	public Retail() {
		super();
	}
	public Retail(String retailInfo, int userId) {
		super();
		this.retailInfo = retailInfo;
		this.userId = userId;
	}
	public Retail(int retailId, String retailInfo, int userId) {
		super();
		this.retailId = retailId;
		this.retailInfo = retailInfo;
		this.userId = userId;
	}
	public int getRetailId() {
		return retailId;
	}
	public void setRetailId(int retailId) {
		this.retailId = retailId;
	}
	public String getRetailInfo() {
		return retailInfo;
	}
	public void setRetailInfo(String retailInfo) {
		this.retailInfo = retailInfo;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
