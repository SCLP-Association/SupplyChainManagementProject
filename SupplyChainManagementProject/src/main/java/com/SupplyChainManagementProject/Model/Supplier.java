package com.SupplyChainManagementProject.Model;

import com.SupplyChainManagementProject.Core.Model.GenericModel;

public class Supplier implements GenericModel<Supplier>{
    private int supplierId;
    private String companyName;
    private int userId;
	public Supplier() {
		super();
	}
	public Supplier(String companyName, int userId) {
		super();
		this.companyName = companyName;
		this.userId = userId;
	}
	public Supplier(int supplierId, String companyName, int userId) {
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
