package com.SupplyChainManagementProject.Model;

public class Supplier {
    private int supplierId;
    private String companyName;
    private String userId;
	public Supplier() {
		super();
	}
	public Supplier(int supplierId, String companyName, String userId) {
		super();
		this.supplierId = supplierId;
		this.companyName = companyName;
		this.userId = userId;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
