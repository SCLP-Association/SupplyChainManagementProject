package com.SupplyChainManagementProject.Service.abstracts;

import java.util.List;

import com.SupplyChainManagementProject.Model.Retail;
import com.SupplyChainManagementProject.Model.Supplier;
import com.SupplyChainManagementProject.Model.User;
import com.SupplyChainManagementProject.ViewModel.UserModel;

public interface IUserService {
	UserModel login(String email,String password);
	void add(User user,Retail retail,Supplier supplier);
	void update(User user,Retail retail,Supplier supplier);
	void delete(int id);
	UserModel findById(int id);
	List<UserModel> getAll();

}
