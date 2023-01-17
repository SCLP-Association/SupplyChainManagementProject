package com.SupplyChainManagementProject.DAO.abstracts;

import java.util.List;

import com.SupplyChainManagementProject.Core.DAO.IGenericDAO;
import com.SupplyChainManagementProject.Model.Product;

public interface IProductDAO extends IGenericDAO<Product>{
	List<Product> searchByProductName(String name);

}
