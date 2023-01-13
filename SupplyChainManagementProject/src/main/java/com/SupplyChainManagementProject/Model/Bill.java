package com.SupplyChainManagementProject.Model;

public class Bill {
	private int billId;
	private double totalAmount;
	private int productId;
	private int supplierId;
	public Bill() {
		super();
	}
	public Bill(int billId, double totalAmount, int productId, int supplierId) {
		super();
		this.billId = billId;
		this.totalAmount = totalAmount;
		this.productId = productId;
		this.supplierId = supplierId;
	}
	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
}
