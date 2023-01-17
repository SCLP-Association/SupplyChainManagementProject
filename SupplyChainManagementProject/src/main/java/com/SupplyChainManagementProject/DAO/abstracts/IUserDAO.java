package com.SupplyChainManagementProject.DAO.abstracts;

import com.SupplyChainManagementProject.Core.DAO.IGenericDAO;
import com.SupplyChainManagementProject.Model.User;

public interface IUserDAO extends IGenericDAO<User> {
	User userLogin(String email,String password);
}
