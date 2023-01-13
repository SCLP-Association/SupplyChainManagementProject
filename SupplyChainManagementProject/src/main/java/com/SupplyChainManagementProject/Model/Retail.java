package com.SupplyChainManagementProject.Model;

public class Retail {
    private int retailId;
    private String retailInfo;
    private String userId;
	public Retail() {
		super();
	}
	public Retail(int retailId, String retailInfo, String userId) {
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
