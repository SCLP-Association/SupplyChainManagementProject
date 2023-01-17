package com.SupplyChainManagementProject.ViewModel;

import java.util.List;

import com.SupplyChainManagementProject.Model.Product;
import com.SupplyChainManagementProject.Model.ProductImage;

public class ProductModel {
	private Product product;
	private List<ProductImage> productImages;
	public ProductModel() {
		super();
	}
	public ProductModel(Product product) {
		super();
		this.product = product;
	}
	public ProductModel(Product product, List<ProductImage> productImages) {
		super();
		this.product = product;
		this.productImages = productImages;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public List<ProductImage> getProductImages() {
		return productImages;
	}
	public void setProductImages(List<ProductImage> productImages) {
		this.productImages = productImages;
	}


}
