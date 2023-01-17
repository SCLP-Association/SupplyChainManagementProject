package com.SupplyChainManagementProject.Model;

import com.SupplyChainManagementProject.Core.Model.GenericModel;

public class Campaign implements GenericModel<Campaign>{
	private int campaignId;
	private String campaignName;
	private double discountOnPercent;
	private double discountOnMoney;
	public Campaign() {
		super();
	}
	public Campaign(String campaignName, double discountOnPercent, double discountOnMoney) {
		super();
		this.campaignName = campaignName;
		this.discountOnPercent = discountOnPercent;
		this.discountOnMoney = discountOnMoney;
	}
	public Campaign(int campaignId, String campaignName, double discountOnPercent, double discountOnMoney) {
		super();
		this.campaignId = campaignId;
		this.campaignName = campaignName;
		this.discountOnPercent = discountOnPercent;
		this.discountOnMoney = discountOnMoney;
	}
	public int getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}
	public String getCampaignName() {
		return campaignName;
	}
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
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

}
