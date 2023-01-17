package com.SupplyChainManagementProject.DAO.concretes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.SupplyChainManagementProject.Core.DAO.GenericDAOImpl;
import com.SupplyChainManagementProject.Core.Helper.Exceptions;
import com.SupplyChainManagementProject.Core.Log.Log;
import com.SupplyChainManagementProject.DAO.abstracts.ICampaignDAO;
import com.SupplyChainManagementProject.Model.Campaign;



public class CampaignDAOImpl extends GenericDAOImpl<Campaign> implements ICampaignDAO{
	private Connection con;
	private ResultSet rs;
	private static final String INSERT_CAMPAIGN_SQL = "INSERT INTO campaign" + "  (campaign_name, discount_on_percent, discount_on_money) OUTPUT Inserted.campaign_id VALUES " +
	        " (?, ?, ?);";
	private static final String UPDATE_CAMPAIGN_SQL = "update campaign set campaign_name = ?,discount_on_percent= ?, discount_on_money = ? where campaign_id = ?;";
	public CampaignDAOImpl(Connection con) {
		super(con);
		this.con=con;
	}

	@Override
	public int add(Campaign campaign) {
		// TODO Auto-generated method stub
        boolean rowUpdated=false;
        int id=0;
        try ( PreparedStatement statement = con.prepareStatement(INSERT_CAMPAIGN_SQL);){
            statement.setString(1, campaign.getCampaignName());
            statement.setDouble(2, campaign.getDiscountOnPercent());
            statement.setDouble(3, campaign.getDiscountOnMoney());
            rs= statement.executeQuery();
            while(rs.next()) {
            	id=rs.getInt("campaign_id");
            }
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        if(rowUpdated) {
        	Log.logger(campaign.getCampaignName()+ " campaign type added.");
        }
        return id;
	}

	@Override
	public void update( Campaign campaign) {
		// TODO Auto-generated method stub
        boolean rowUpdated=false;
        try ( PreparedStatement statement = con.prepareStatement(UPDATE_CAMPAIGN_SQL);) {
            statement.setString(1, campaign.getCampaignName());
            statement.setDouble(2, campaign.getDiscountOnPercent());
            statement.setDouble(3, campaign.getDiscountOnMoney());
            statement.setInt(4, campaign.getCampaignId());

            rowUpdated = statement.executeUpdate() > 0;
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        if(rowUpdated) {
        	Log.logger(campaign.getCampaignName() + " campaign updated.");
        }
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		if(delete("campaign",id)) { 
			Log.logger("campaign deleted");
		}
	}

	@Override
	public Campaign findById(int id) {
		// TODO Auto-generated method stub
		   rs =findById("campaign",id); 
		   Campaign campaign = convertToModel(rs);
		   return campaign; 
	}

	@Override
	public List<Campaign> getAll() {
		// TODO Auto-generated method stub
		   rs =getAll("campaign"); 
		   List<Campaign> campaigns= convertToList(rs);
		   return campaigns; 
	}
	private Campaign convertToModel(ResultSet rs)  {
		Campaign  campaign= new Campaign ();
		try{
			while (rs.next()) {
				campaign.setCampaignId(rs.getInt("campaign_id"));
				campaign.setCampaignName(rs.getString("campaign_name"));
				campaign.setDiscountOnPercent(rs.getDouble("discount_on_percent"));
				campaign.setDiscountOnMoney(rs.getDouble("discount_on_money"));
			}
		}catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
		return campaign;
	}
	private List<Campaign> convertToList(ResultSet rs)  {
		List<Campaign> campaignList= new ArrayList<Campaign>();
		Campaign  campaign= new Campaign ();
		try{
			while (rs.next()) {
				campaign.setCampaignId(rs.getInt("campaign_id"));
				campaign.setCampaignName(rs.getString("campaign_name"));
				campaign.setDiscountOnPercent(rs.getDouble("discount_on_percent"));
				campaign.setDiscountOnMoney(rs.getDouble("discount_on_money"));
				campaignList.add(campaign);
			}
		}catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
		return campaignList;
	}

}
