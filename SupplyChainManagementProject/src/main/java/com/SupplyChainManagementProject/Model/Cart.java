package com.SupplyChainManagementProject.Model;

import com.SupplyChainManagementProject.Core.Model.GenericModel;

public class Cart implements GenericModel<Cart>{
	private int cartId;
	private int quantity;
	private int productId;
	public Cart() {
		super();
	}
	public Cart(int quantity, int productId) {
		super();
		this.quantity = quantity;
		this.productId = productId;
	}
	public Cart(int cartId, int quantity, int productId) {
		super();
		this.cartId = cartId;
		this.quantity = quantity;
		this.productId = productId;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}

}
