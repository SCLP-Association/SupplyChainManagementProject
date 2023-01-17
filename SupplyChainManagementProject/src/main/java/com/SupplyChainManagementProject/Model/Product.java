package com.SupplyChainManagementProject.Model;

import com.SupplyChainManagementProject.Core.Model.GenericModel;

public class Product implements GenericModel<Product>{
	private int productId;
	private String productName;
	private double quantity;
	private double price;
	private String explanation;
	private boolean status;
	public Product() {
		super();
	}
	public Product(String productName, double quantity, double price, String explanation, boolean status) {
		super();
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.explanation = explanation;
		this.status = status;
	}
	public Product(int productId, String productName, double quantity, double price, String explanation,
			boolean status) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.explanation = explanation;
		this.status = status;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}

