package com.SupplyChainManagementProject.DAO.abstracts;

import com.SupplyChainManagementProject.Core.DAO.IGenericDAO;
import com.SupplyChainManagementProject.Model.Retail;

public interface IRetailDAO extends IGenericDAO<Retail>{
	Retail findByUserId(int id);
}
