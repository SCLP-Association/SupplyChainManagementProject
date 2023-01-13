package com.SupplyChainManagementProject.Model;

public class ProductImage {
	private int productImageId;
	private String image;
	private int productId;
	public ProductImage() {
		super();
	}
	public ProductImage(int productImageId, String image, int productId) {
		super();
		this.productImageId = productImageId;
		this.image = image;
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

}
