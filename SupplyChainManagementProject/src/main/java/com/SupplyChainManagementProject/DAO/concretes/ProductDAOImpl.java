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
import com.SupplyChainManagementProject.DAO.abstracts.IProductDAO;
import com.SupplyChainManagementProject.Model.Product;

public class ProductDAOImpl extends GenericDAOImpl<Product> implements IProductDAO{
	private Connection con;
	private ResultSet rs;
	private static final String INSERT_PRODUCT_SQL = "INSERT INTO product" + "  (product_name, quantity, price,explanation,status) OUTPUT Inserted.product_id VALUES " +
	        " (?, ?, ?,?,?);";
	private static final String UPDATE_PRODUCT_SQL = "update product set product_name = ?,quantity= ?, price = ?,explanation = ?,status=? where product_id = ?;";
	private static final String SEARCH_PRODUCT_SQL = "SELECT * FROM product where product_name = ?;";
	public ProductDAOImpl(Connection con) {
		super(con);
		this.con=con;
		
	}

	@Override
	public int add(Product product) {
		// TODO Auto-generated method stub
        boolean rowUpdated=false;
        int id=0;
        try ( PreparedStatement statement = con.prepareStatement(INSERT_PRODUCT_SQL);){
            statement.setString(1, product.getProductName());
            statement.setDouble(2, product.getQuantity());
            statement.setDouble(3, product.getPrice());
            statement.setString(4, product.getExplanation());
            statement.setBoolean(5, product.isStatus());

            rs= statement.executeQuery();
            while(rs.next()) {
            	id=rs.getInt("product_id");
            }
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        if(rowUpdated) {
        	Log.logger(product.getProductName()+ " product added.");
        }
        return id;
	}

	@Override
	public void update( Product product) {
		// TODO Auto-generated method stub
        boolean rowUpdated=false;
        try ( PreparedStatement statement = con.prepareStatement(UPDATE_PRODUCT_SQL);) {
            statement.setString(1, product.getProductName());
            statement.setDouble(2, product.getQuantity());
            statement.setDouble(3, product.getPrice());
            statement.setString(4, product.getExplanation());
            statement.setBoolean(5, product.isStatus());
            statement.setInt(6, product.getProductId());

            rowUpdated = statement.executeUpdate() > 0;
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        if(rowUpdated) {
        	Log.logger(product.getProductName()+ " product updated.");
        }
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		if(delete("PRODUCT",id)) { 
			Log.logger("product deleted");
		}
	}

	@Override
	public Product findById(int id) {
		// TODO Auto-generated method stub
		   rs =findById("PRODUCT",id); 
		   Product product = convertToModel(rs);
		   return product; 
	}

	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		   rs =getAll("PRODUCT"); 
		   List<Product> products = convertToList(rs);
		   return products; 
	}
	private Product convertToModel(ResultSet rs)  {
		Product product= new Product();
		try{
			while (rs.next()) {
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setQuantity(rs.getDouble("quantity"));
				product.setPrice(rs.getDouble("price"));
				product.setExplanation(rs.getString("explanation"));
				product.setStatus(rs.getBoolean("status"));
			}
		}catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
		return product;
	}
	private List<Product> convertToList(ResultSet rs)  {
		List<Product> productList= new ArrayList<Product>();
		Product product= new Product();
		try{
			while (rs.next()) {
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setQuantity(rs.getDouble("quantity"));
				product.setPrice(rs.getDouble("price"));
				product.setExplanation(rs.getString("explanation"));
				product.setStatus(rs.getBoolean("status"));
				productList.add(product);
			}
		}catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
		return productList;
	}

	@Override
	public List<Product> searchByProductName(String name) {   
        try(PreparedStatement pStmt= con.prepareStatement(SEARCH_PRODUCT_SQL);) {
            pStmt.setString(1, name);
            rs = pStmt.executeQuery();
        }catch (SQLException ex) {
        	Exceptions.printSQLException(ex);
        }
        List<Product> products = convertToList(rs);
        return products; 
	}

}
