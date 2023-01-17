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
import com.SupplyChainManagementProject.DAO.abstracts.ISupplierDAO;
import com.SupplyChainManagementProject.Model.Supplier;

public class SupplierDAOImpl  extends GenericDAOImpl<Supplier> implements ISupplierDAO{
	private Connection con;
	private ResultSet rs;
	private static final String INSERT_SUPPLIER_SQL = "INSERT INTO supplier" + "  (company_name,user_id) OUTPUT Inserted.supplier_id VALUES " +
	        " (?,?);";
	private static final String UPDATE_SUPPLIER_SQL = "update supplier set company_name = ?,user_id = ? where supplier_id = ?;";
	private static final String FINDBYUSERID_SQL ="SELECT * FROM  supplier WHERE user_id = ?;";
	public SupplierDAOImpl(Connection con) {
		super(con);
		this.con=con;
	}

	@Override
	public int add(Supplier supplier) {
        boolean rowUpdated=false;
        int id=0;
        try ( PreparedStatement statement = con.prepareStatement(INSERT_SUPPLIER_SQL);){
            statement.setString(1, supplier.getCompanyName());
            statement.setInt(2, supplier.getUserId());
            rs= statement.executeQuery();
            while(rs.next()) {
            	id=rs.getInt("supplier_id");
            }
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        if(rowUpdated) {
        	Log.logger(supplier.getSupplierId()+ " supplier added.");
        }
        return id;
		
	}

	@Override
	public void update( Supplier supplier) {
        boolean rowUpdated=false;
        try ( PreparedStatement statement = con.prepareStatement(UPDATE_SUPPLIER_SQL);) {
            statement.setString(1, supplier.getCompanyName());
            statement.setInt(2, supplier.getUserId());
            statement.setInt(3, supplier.getSupplierId());

            rowUpdated = statement.executeUpdate() > 0;
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        if(rowUpdated) {
        	Log.logger(supplier.getSupplierId() + " supplier updated.");
        }
		
	}

	@Override
	public void delete(int id) {
		if(delete("supplier",id)) { 
			Log.logger("supplier deleted");
		}	
		
	}

	@Override
	public Supplier findById(int id) {
		   rs =findById("supplier",id); 
		   Supplier supplier = convertToModel(rs);
		   return supplier; 
	}

	@Override
	public List<Supplier> getAll() {
		   rs =getAll("supplier"); 
		   List<Supplier> suppliers= convertToList(rs);
		   return suppliers; 
	}
	private Supplier convertToModel(ResultSet rs)  {
		Supplier  supplier= new Supplier ();
		try{
			while (rs.next()) {
				supplier.setSupplierId(rs.getInt("supplier_id"));
				supplier.setCompanyName(rs.getString("company_name"));
				supplier.setUserId(rs.getInt("user_id"));
			}
		}catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
		return supplier;
	}
	private List<Supplier> convertToList(ResultSet rs)  {
		List<Supplier> supplierList= new ArrayList<Supplier>();
		Supplier  supplier = new Supplier();
		try{
			while (rs.next()) {
				supplier.setSupplierId(rs.getInt("supplier_id"));
				supplier.setCompanyName(rs.getString("company_name"));
				supplier.setUserId(rs.getInt("user_id"));
				supplierList.add(supplier);
			}
		}catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
		return supplierList;
	}

	@Override
	public Supplier findByUserId(int id) {
		Supplier supplier=new Supplier();
        try ( PreparedStatement statement = con.prepareStatement(FINDBYUSERID_SQL);){
            statement.setInt(1, id);
            rs= statement.executeQuery();
            supplier = convertToModel(rs);
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        return supplier;
	}

}
