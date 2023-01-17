package com.SupplyChainManagementProject.DAO.abstracts;

import com.SupplyChainManagementProject.Core.DAO.IGenericDAO;
import com.SupplyChainManagementProject.Model.Supplier;

public interface ISupplierDAO extends IGenericDAO<Supplier>{
	Supplier findByUserId(int id);
}
