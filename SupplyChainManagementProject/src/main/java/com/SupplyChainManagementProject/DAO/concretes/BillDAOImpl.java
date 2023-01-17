package com.SupplyChainManagementProject.DAO.concretes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.SupplyChainManagementProject.Core.DAO.GenericDAOImpl;
import com.SupplyChainManagementProject.Core.Helper.Exceptions;
import com.SupplyChainManagementProject.Core.Log.Log;
import com.SupplyChainManagementProject.DAO.abstracts.IBillDAO;
import com.SupplyChainManagementProject.Model.Bill;

public class BillDAOImpl extends GenericDAOImpl<Bill> implements IBillDAO{
	private Connection con;
	private ResultSet rs;
	private static final String INSERT_BILL_SQL = "INSERT INTO bill" + "  (total_amount, product_id, supplier_id,bill_date) OUTPUT Inserted.bill_id VALUES " +
	        " (?, ?, ?,?);";
	private static final String UPDATE_BILL_SQL = "update bill set total_amount = ?,product_id= ?, supplier_id = ?,bill_date = ? where bill_id = ?;";
	public BillDAOImpl(Connection con) {
		super(con);
		this.con=con;
	}

	@Override
	public int add(Bill bill) {
        boolean rowUpdated=false;
        int id=0;
        try ( PreparedStatement statement = con.prepareStatement(INSERT_BILL_SQL);){
            statement.setDouble(1, bill.getTotalAmount());
            statement.setInt(2, bill.getProductId());
            statement.setInt(3, bill.getSupplierId());
            statement.setDate(4, (Date) bill.getBillDate());
            rs= statement.executeQuery();
            while(rs.next()) {
            	id=rs.getInt("user_id");
            }
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        if(rowUpdated) {
        	Log.logger(bill.getBillId()+ " bill  added.");
        }
		return id;
	}

	@Override
	public void update(Bill bill) {
        boolean rowUpdated=false;
        try ( PreparedStatement statement = con.prepareStatement(UPDATE_BILL_SQL);) {
            statement.setDouble(1, bill.getTotalAmount());
            statement.setInt(2, bill.getProductId());
            statement.setInt(3, bill.getSupplierId());
            statement.setDate(4, (Date) bill.getBillDate());
            statement.setInt(5, bill.getBillId());

            rowUpdated = statement.executeUpdate() > 0;
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        if(rowUpdated) {
        	Log.logger(bill.getBillId() + " bill updated.");
        }
		
	}

	@Override
	public void delete(int id) {
		if(delete("bill",id)) { 
			Log.logger("bill deleted");
		}
		
	}

	@Override
	public Bill findById(int id) {
		   rs =findById("bill",id); 
		   Bill bill = convertToModel(rs);
		   return bill; 
	}

	@Override
	public List<Bill> getAll() {
		// TODO Auto-generated method stub
		   rs =getAll("bill"); 
		   List<Bill> bills= convertToList(rs);
		   return bills; 
	}
	private Bill convertToModel(ResultSet rs)  {
		Bill  bill= new Bill ();
		try{
			while (rs.next()) {
				bill.setBillId(rs.getInt("bill_id"));
				bill.setTotalAmount(rs.getDouble("total_amount"));
				bill.setProductId(rs.getInt("product_id"));
				bill.setSupplierId(rs.getInt("supplier_id"));
				bill.setBillDate(rs.getDate("bill_date"));
			}
		}catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
		return bill;
	}
	private List<Bill> convertToList(ResultSet rs)  {
		List<Bill> billList= new ArrayList<Bill>();
		Bill  bill= new Bill();
		try{
			while (rs.next()) {
				bill.setBillId(rs.getInt("bill_id"));
				bill.setTotalAmount(rs.getDouble("total_amount"));
				bill.setProductId(rs.getInt("product_id"));
				bill.setSupplierId(rs.getInt("supplier_id"));
				bill.setBillDate(rs.getDate("bill_date"));
				billList.add(bill);
			}
		}catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
		return billList;
	}

}
