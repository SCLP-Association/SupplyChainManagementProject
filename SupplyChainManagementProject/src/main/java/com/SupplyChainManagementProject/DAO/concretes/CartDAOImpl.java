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
import com.SupplyChainManagementProject.DAO.abstracts.ICartDAO;
import com.SupplyChainManagementProject.Model.Cart;

public class CartDAOImpl extends GenericDAOImpl<Cart> implements ICartDAO{
	private Connection con;
	private ResultSet rs;
	private static final String INSERT_CART_SQL = "INSERT INTO cart" + "  (quantity, product_id) OUTPUT Inserted.campaign_id VALUES " +
	        " (?, ?);";
	private static final String UPDATE_CART_SQL = "update cart set quantity = ?,product_id= ? where cart_id = ?;";
	public CartDAOImpl(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int add(Cart cart) {
		// TODO Auto-generated method stub
        boolean rowUpdated=false;
        int id=0;
        try ( PreparedStatement statement = con.prepareStatement(INSERT_CART_SQL);){
            statement.setInt(1, cart.getQuantity());
            statement.setInt(2, cart.getProductId());
            rs= statement.executeQuery();
            while(rs.next()) {
            	id=rs.getInt("cart_id");
            }
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        if(rowUpdated) {
        	Log.logger(cart.getCartId()+ " cart added.");
        }
        return id;
	}

	@Override
	public void update(Cart cart) {
        boolean rowUpdated=false;
        try ( PreparedStatement statement = con.prepareStatement(UPDATE_CART_SQL);) {
            statement.setInt(1, cart.getQuantity());
            statement.setInt(2, cart.getProductId());
            statement.setInt(3, cart.getCartId());
            rowUpdated = statement.executeUpdate() > 0;
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        if(rowUpdated) {
        	Log.logger(cart.getCartId() + " cart updated.");
        }
		
	}

	@Override
	public void delete(int id) {
		if(delete("cart",id)) { 
			Log.logger("cart deleted");
		}
		
	}

	@Override
	public Cart findById(int id) {
		   rs =findById("cart",id); 
		   Cart cart = convertToModel(rs);
		   return cart; 
	}

	@Override
	public List<Cart> getAll() {
		   rs =getAll("cart"); 
		   List<Cart> carts= convertToList(rs);
		   return carts; 
	}
	private Cart convertToModel(ResultSet rs)  {
		Cart  cart= new Cart ();
		try{
			while (rs.next()) {
				cart.setCartId(rs.getInt("cart_id"));
				cart.setQuantity(rs.getInt("quantity"));
				cart.setProductId(rs.getInt("product_id"));
			}
		}catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
		return cart;
	}
	private List<Cart> convertToList(ResultSet rs)  {
		List<Cart> cartList= new ArrayList<Cart>();
		Cart  cart= new Cart ();
		try{
			while (rs.next()) {
				cart.setCartId(rs.getInt("cart_id"));
				cart.setQuantity(rs.getInt("quantity"));
				cart.setProductId(rs.getInt("product_id"));
				cartList.add(cart);
			}
		}catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
		return cartList;
	}

}
