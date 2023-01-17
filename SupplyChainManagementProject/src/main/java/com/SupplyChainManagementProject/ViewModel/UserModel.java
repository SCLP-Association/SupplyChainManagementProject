package com.SupplyChainManagementProject.ViewModel;


import com.SupplyChainManagementProject.Model.Retail;
import com.SupplyChainManagementProject.Model.Supplier;
import com.SupplyChainManagementProject.Model.User;

public class UserModel {
	private User user;
	private Retail retail;
	private Supplier supplier;
	public UserModel() {
		super();
	}
	public UserModel(User user) {
		super();
		this.user = user;
	}
	public UserModel(User user, Retail retail) {
		super();
		this.user = user;
		this.retail = retail;
	}
	public UserModel(User user, Supplier supplier) {
		super();
		this.user = user;
		this.supplier = supplier;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Retail getRetail() {
		return retail;
	}
	public void setRetail(Retail retail) {
		this.retail = retail;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}


}
