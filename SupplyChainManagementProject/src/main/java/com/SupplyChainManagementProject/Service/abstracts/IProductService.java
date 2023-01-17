package com.SupplyChainManagementProject.Service.abstracts;


import java.util.List;

import com.SupplyChainManagementProject.Model.Product;
import com.SupplyChainManagementProject.Model.ProductImage;
import com.SupplyChainManagementProject.ViewModel.ProductModel;

public interface IProductService {
	void add(Product product,List<ProductImage> images);
	void update(Product product,List<ProductImage> images);
	void delete(int id);
	ProductModel findById(int id);
	List<ProductModel> getAll();
	List<ProductModel> searchByProductName(String name);
	
	
	
}
