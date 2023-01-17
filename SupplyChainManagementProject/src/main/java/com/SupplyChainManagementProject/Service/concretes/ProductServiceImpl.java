package com.SupplyChainManagementProject.Service.concretes;


import java.util.ArrayList;
import java.util.List;

import com.SupplyChainManagementProject.Core.DbConnection.PostgreSqlDbConnection;
import com.SupplyChainManagementProject.DAO.abstracts.IProductDAO;
import com.SupplyChainManagementProject.DAO.abstracts.IProductImageDAO;
import com.SupplyChainManagementProject.DAO.concretes.ProductDAOImpl;
import com.SupplyChainManagementProject.DAO.concretes.ProductImageDAOImpl;
import com.SupplyChainManagementProject.Model.Product;
import com.SupplyChainManagementProject.Model.ProductImage;
import com.SupplyChainManagementProject.Service.abstracts.IProductService;
import com.SupplyChainManagementProject.ViewModel.ProductModel;

public class ProductServiceImpl implements IProductService{
	private IProductDAO productDAO;
	private IProductImageDAO productImageDAO;
	public ProductServiceImpl(IProductDAO productDAO, IProductImageDAO productImageDAO) {
		super();
		this.productDAO = new ProductDAOImpl(PostgreSqlDbConnection.getConnection());
		this.productImageDAO = new ProductImageDAOImpl(PostgreSqlDbConnection.getConnection());
	}
	@Override
	public void add(Product product, List<ProductImage> images) {
		int id=this.productDAO.add(product);
		if(images!=null) {
			for(ProductImage image:images) {
				image.setProductId(id);
				this.productImageDAO.add(image);
			}
		}

		
	}
	@Override
	public void update(Product product, List<ProductImage> images) {
		this.productDAO.update(product);
		if(images!=null) {
			for(ProductImage image:images) {
				this.productImageDAO.update(image);
			}
		}
		
	}
	@Override
	public void delete(int id) {
		this.productDAO.delete(id);	
		List<ProductImage> productImages=this.productImageDAO.findByProductId(id);
		for(ProductImage image:productImages) {
			this.productImageDAO.delete(image.getProductImageId());
		}
		
	}
	@Override
	public ProductModel findById(int id) {
		ProductModel productModel=new ProductModel(this.productDAO.findById(id));
		if(this.productImageDAO.findByProductId(id)!=null) {
			productModel.setProductImages(this.productImageDAO.findByProductId(id));
		}
		return productModel;
	}
	@Override
	public List<ProductModel> getAll() {
		List<ProductModel> productModels=new ArrayList<ProductModel>();
		ProductModel productModel=new ProductModel();
		for(Product product:this.productDAO.getAll()) {
			productModel.setProduct(product);
			if(this.productImageDAO.findByProductId(product.getProductId())!=null) {
				productModel.setProductImages(this.productImageDAO.findByProductId(product.getProductId()));

			}
			productModels.add(productModel);
		}
		return productModels;
	}
	@Override
	public List<ProductModel> searchByProductName(String name) {
		List<ProductModel> productModels=new ArrayList<ProductModel>();
		ProductModel productModel=new ProductModel();
		if(this.productDAO.searchByProductName(name)!=null) {
			for(Product product:this.productDAO.searchByProductName(name)) {
				productModel.setProduct(product);
				if(this.productImageDAO.findByProductId(product.getProductId())!=null) {
					productModel.setProductImages(this.productImageDAO.findByProductId(product.getProductId()));

				}
				productModels.add(productModel);
			}
		}
		return productModels;
	}


}
