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
import com.SupplyChainManagementProject.DAO.abstracts.IDiscountDAO;
import com.SupplyChainManagementProject.Model.Discount;

public class DiscountDAOImpl extends GenericDAOImpl<Discount> implements IDiscountDAO{
	private Connection con;
	private ResultSet rs;
	private static final String INSERT_DISCOUNT_SQL = "INSERT INTO discount" + "  ( discount_on_percent, discount_on_money,retail_id,supplier_id) OUTPUT Inserted.discount_id VALUES " +
	        " (?, ?, ?);";
	private static final String UPDATE_DISCOUNT_SQL = "update discount set discount_on_percent= ?, discount_on_money = ?,retail_id = ?,supplier_id = ? where discount_id = ?;";
	public DiscountDAOImpl(Connection con) {
		super(con);
		this.con=con;
	}

	@Override
	public int add(Discount discount) {
		// TODO Auto-generated method stub
        boolean rowUpdated=false;
        int id=0;
        try ( PreparedStatement statement = con.prepareStatement(INSERT_DISCOUNT_SQL);){
            statement.setDouble(1, discount.getDiscountOnPercent());
            statement.setDouble(2, discount.getDiscountOnMoney());
            statement.setInt(3, discount.getRetailId());
            statement.setInt(4, discount.getSupplierId());
            rs= statement.executeQuery();
            while(rs.next()) {
            	id=rs.getInt("discount_id");
            }
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        if(rowUpdated) {
        	Log.logger(discount.getDiscountId()+ " discount added.");
        }
        return id;
	}

	@Override
	public void update( Discount discount) {
		// TODO Auto-generated method stub
        boolean rowUpdated=false;
        try ( PreparedStatement statement = con.prepareStatement(UPDATE_DISCOUNT_SQL);) {
            statement.setDouble(1, discount.getDiscountOnPercent());
            statement.setDouble(2, discount.getDiscountOnMoney());
            statement.setInt(3, discount.getRetailId());
            statement.setInt(4, discount.getSupplierId());
            statement.setInt(5, discount.getDiscountId());

            rowUpdated = statement.executeUpdate() > 0;
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        if(rowUpdated) {
        	Log.logger(discount.getDiscountId()+ " discount updated.");
        }
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		if(delete("discount",id)) { 
			Log.logger("discount deleted");
		}
	}

	@Override
	public Discount findById(int id) {
		// TODO Auto-generated method stub
		   rs =findById("discount",id); 
		   Discount discount = convertToModel(rs);
		   return discount; 
	}

	@Override
	public List<Discount> getAll() {
		// TODO Auto-generated method stub
		   rs =getAll("discount"); 
		   List<Discount> discounts= convertToList(rs);
		   return discounts; 
	}
	private Discount convertToModel(ResultSet rs)  {
		Discount  discount= new Discount ();
		try{
			while (rs.next()) {
				discount.setDiscountOnPercent(rs.getDouble("discount_on_percent"));
				discount.setDiscountOnMoney(rs.getDouble("discount_on_money"));
				discount.setRetailId(rs.getInt("retail_id"));
				discount.setSupplierId(rs.getInt("supplier_id"));
			}
		}catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
		return discount;
	}
	private List<Discount> convertToList(ResultSet rs)  {
		List<Discount> discountList= new ArrayList<Discount>();
		Discount  discount= new Discount ();
		try{
			while (rs.next()) {
				discount.setDiscountOnPercent(rs.getDouble("discount_on_percent"));
				discount.setDiscountOnMoney(rs.getDouble("discount_on_money"));
				discount.setRetailId(rs.getInt("retail_id"));
				discount.setSupplierId(rs.getInt("supplier_id"));
				discountList.add(discount);
			}
		}catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
		return discountList;
	}

}
