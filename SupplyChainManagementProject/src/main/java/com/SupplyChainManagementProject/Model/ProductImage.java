package com.SupplyChainManagementProject.Model;

import com.SupplyChainManagementProject.Core.Model.GenericModel;

public class ProductImage implements GenericModel<ProductImage>{
	private int productImageId;
	private String image;
	private int position;
	private int productId;
	public ProductImage() {
		super();
	}
	public ProductImage(String image, int position, int productId) {
		super();
		this.image = image;
		this.position = position;
		this.productId = productId;
	}
	public ProductImage(int productImageId, String image, int position, int productId) {
		super();
		this.productImageId = productImageId;
		this.image = image;
		this.position = position;
		this.productId = productId;
	}
	public int getProductImageId() {
		return productImageId;
	}
	public void setProductImageId(int productImageId) {
		this.productImageId = productImageId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}

}
