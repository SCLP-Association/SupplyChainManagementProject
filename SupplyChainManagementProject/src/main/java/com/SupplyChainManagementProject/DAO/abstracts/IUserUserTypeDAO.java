package com.SupplyChainManagementProject.DAO.abstracts;

import com.SupplyChainManagementProject.Core.DAO.IGenericDAO;
import com.SupplyChainManagementProject.Model.UserUserType;

public interface IUserUserTypeDAO extends IGenericDAO<UserUserType>{
	UserUserType findByUserId(int id);

}
