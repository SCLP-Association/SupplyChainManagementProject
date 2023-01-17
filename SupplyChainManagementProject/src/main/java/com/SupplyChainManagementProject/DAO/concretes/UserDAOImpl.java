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
import com.SupplyChainManagementProject.DAO.abstracts.IUserDAO;
import com.SupplyChainManagementProject.Model.User;



public class UserDAOImpl extends GenericDAOImpl<User>implements IUserDAO{
	private Connection con;
	private ResultSet rs;
	private static final String LOGIN_USER_SQL = "select * from user where email=? and password=?;";
	private static final String INSERT_USER_SQL = "INSERT INTO user" + "  (name, email, phone_number,password) OUTPUT Inserted.user_id VALUES " +
	        " (?, ?, ?,?);";
	private static final String UPDATE_USER_SQL = "update user set name = ?,email= ?, phone_number = ?,password = ? where user_id = ?;";
	public UserDAOImpl(Connection con) {
		super(con);
		this.con = con;
	}
	@Override
	public User userLogin(String email,String password) {
		User user=null;
		try ( PreparedStatement statement = con.prepareStatement(LOGIN_USER_SQL);){
			statement.setString(1, email);
			statement.setString(2, password);
			rs=statement.executeQuery();
			if(rs.next()) {
				user=new User();
				user.setUserId(rs.getInt("user_id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setPhoneNumber(rs.getString("phone_number"));
				
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return user;
	}
	@Override
	public int add(User user) {
		// TODO Auto-generated method stub
        boolean rowUpdated=false;
        int id=0;
        try ( PreparedStatement statement = con.prepareStatement(INSERT_USER_SQL);){
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPhoneNumber());
            statement.setString(4, user.getPassword());

            rs= statement.executeQuery();
            while(rs.next()) {
            	id=rs.getInt("user_id");
            }
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        if(rowUpdated) {
        	Log.logger(user.getEmail()+ " user added.");
        }
        return id;
	}
	@Override
	public void update( User user) {
		// TODO Auto-generated method stub
        boolean rowUpdated=false;
        try ( PreparedStatement statement = con.prepareStatement(UPDATE_USER_SQL);) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPhoneNumber());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getUserId());

            rowUpdated = statement.executeUpdate() > 0;
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        if(rowUpdated) {
        	Log.logger(user.getEmail()+ " user updated.");
        }
		
	}
	@Override
	public User findById(int id){
		   rs =findById("USER",id); 
		   User user = convertToModel(rs);
		   return user; 
	}
	@Override
	public List<User> getAll(){
		   rs =getAll("USER"); // "USER" is table name in DB
		   List<User> users = convertToList(rs);
		   return users; 
	}
	@Override
	public void delete(int id){
		if(delete("USER",id)) { 
			Log.logger("user deleted");
		}
	}
	private User convertToModel(ResultSet rs)  {
		User user= new User();
		try{
			while (rs.next()) {
				user.setUserId(rs.getInt("user_id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setPhoneNumber(rs.getString("phone_number"));
			}
		}catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
		return user;
	}
	private List<User> convertToList(ResultSet rs)  {
		List<User> userList= new ArrayList<User>();
		User user= new User();
		try{
			while (rs.next()) {
				user.setUserId(rs.getInt("user_id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setPhoneNumber(rs.getString("phone_number"));
				userList.add(user);
			}
		}catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
		return userList;
	}
}

