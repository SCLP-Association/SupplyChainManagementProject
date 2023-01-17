package com.SupplyChainManagementProject.Model;

import com.SupplyChainManagementProject.Core.Model.GenericModel;

public class User implements GenericModel<User>{
    private int userId;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    
	public User() {
		super();
	}
	public User(String name, String email, String password, String phoneNumber) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}
	public User(int userId, String name, String email, String password, String phoneNumber) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
