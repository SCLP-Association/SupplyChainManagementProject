package com.SupplyChainManagementProject.Model;

public class Discount {
	private int discountId;
	private double discountOnPercent;
	private double discountOnMoney;
	private int retailId;
	private int supplierId;
	public Discount() {
		super();
	}
	public Discount(int discountId, double discountOnPercent, double discountOnMoney, int retailId, int supplierId) {
		super();
		this.discountId = discountId;
		this.discountOnPercent = discountOnPercent;
		this.discountOnMoney = discountOnMoney;
		this.retailId = retailId;
		this.supplierId = supplierId;
	}
	public int getDiscountId() {
		return discountId;
	}
	public void setDiscountId(int discountId) {
		this.discountId = discountId;
	}
	public double getDiscountOnPercent() {
		return discountOnPercent;
	}
	public void setDiscountOnPercent(double discountOnPercent) {
		this.discountOnPercent = discountOnPercent;
	}
	public double getDiscountOnMoney() {
		return discountOnMoney;
	}
	public void setDiscountOnMoney(double discountOnMoney) {
		this.discountOnMoney = discountOnMoney;
	}
	public int getRetailId() {
		return retailId;
	}
	public void setRetailId(int retailId) {
		this.retailId = retailId;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

}
