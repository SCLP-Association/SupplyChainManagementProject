package com.SupplyChainManagementProject.Model;

import java.util.Date;

import com.SupplyChainManagementProject.Core.Model.GenericModel;

public class Bill implements GenericModel<Bill>{
	private int billId;
	private double totalAmount;
	private Date billDate;
	private int productId;
	private int supplierId;
	public Bill() {
		super();
	}
	public Bill(double totalAmount, Date billDate, int productId, int supplierId) {
		super();
		this.totalAmount = totalAmount;
		this.billDate = billDate;
		this.productId = productId;
		this.supplierId = supplierId;
	}
	public Bill(int billId, double totalAmount, Date billDate, int productId, int supplierId) {
		super();
		this.billId = billId;
		this.totalAmount = totalAmount;
		this.billDate = billDate;
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
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
}
