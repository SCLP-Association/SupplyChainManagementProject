package com.SupplyChainManagementProject.DAO.abstracts;

import java.util.List;

import com.SupplyChainManagementProject.Core.DAO.IGenericDAO;
import com.SupplyChainManagementProject.Model.ProductImage;

public interface IProductImageDAO extends IGenericDAO<ProductImage>{
	List<ProductImage> findByProductId(int id);
}
