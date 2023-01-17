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
import com.SupplyChainManagementProject.DAO.abstracts.IRetailDAO;
import com.SupplyChainManagementProject.Model.Retail;

public class RetailDAOImpl extends GenericDAOImpl<Retail> implements IRetailDAO{
	private Connection con;
	private ResultSet rs;
	private static final String INSERT_RETAIL_SQL = "INSERT INTO retail" + "  (retail_info,user_id) OUTPUT Inserted.retail_id VALUES " +
	        " (?,?);";
	private static final String UPDATE_RETAIL_SQL = "update retail set retail_info = ?,user_id = ? where retail_id = ?;";
	private static final String FINDBYUSERID_SQL ="SELECT * FROM  retail WHERE user_id = ?;";


	public RetailDAOImpl(Connection con) {
		super(con);
		this.con=con;
	}

	@Override
	public int add(Retail retail) {
		// TODO Auto-generated method stub
        boolean rowUpdated=false;
        int id=0;
        try ( PreparedStatement statement = con.prepareStatement(INSERT_RETAIL_SQL);){
            statement.setString(1, retail.getRetailInfo());
            statement.setInt(2, retail.getUserId());
            rs= statement.executeQuery();
            while(rs.next()) {
            	id=rs.getInt("retail_id");
            }
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        if(rowUpdated) {
        	Log.logger(retail.getRetailId()+ " retail added.");
        }
        return id;
	}

	@Override
	public void update( Retail retail) {
        boolean rowUpdated=false;
        try ( PreparedStatement statement = con.prepareStatement(UPDATE_RETAIL_SQL);) {
            statement.setString(1, retail.getRetailInfo());
            statement.setInt(2, retail.getUserId());
            statement.setInt(3, retail.getRetailId());
            rowUpdated = statement.executeUpdate() > 0;
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        if(rowUpdated) {
        	Log.logger(retail.getRetailId() + " retail updated.");
        }
		
	}

	@Override
	public void delete(int id) {
		if(delete("retail",id)) { 
			Log.logger("retail deleted");
		}		
	}

	@Override
	public Retail findById(int id) {
		   rs =findById("retail",id); 
		   Retail retail = convertToModel(rs);
		   return retail; 
	}

	@Override
	public List<Retail> getAll() {
		   rs =getAll("retail"); 
		   List<Retail> retails= convertToList(rs);
		   return retails; 
	}
	private Retail convertToModel(ResultSet rs)  {
		 Retail  retail= new Retail ();
		try{
			while (rs.next()) {
				retail.setRetailId(rs.getInt("retail_id"));
				retail.setRetailInfo(rs.getString("retail_info"));
				retail.setUserId(rs.getInt("user_id"));
			}
		}catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
		return retail;
	}
	private List<Retail> convertToList(ResultSet rs)  {
		List<Retail> retailList= new ArrayList<Retail>();
		Retail  retail= new Retail();
		try{
			while (rs.next()) {
				retail.setRetailId(rs.getInt("retail_id"));
				retail.setRetailInfo(rs.getString("retail_info"));
				retail.setUserId(rs.getInt("user_id"));
				retailList.add(retail);
			}
		}catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
		return retailList;
	}

	@Override
	public Retail findByUserId(int id) {
        Retail retail=new Retail();
        try ( PreparedStatement statement = con.prepareStatement(FINDBYUSERID_SQL);){
            statement.setInt(1, id);
            rs= statement.executeQuery();
            retail = convertToModel(rs);
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        return retail;
	}
}
