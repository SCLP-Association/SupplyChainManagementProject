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
import com.SupplyChainManagementProject.DAO.abstracts.IUserAuthorizationDAO;
import com.SupplyChainManagementProject.Model.UserAuthorization;

public class UserAuthorizationDAOImpl extends GenericDAOImpl<UserAuthorization> implements IUserAuthorizationDAO{
	private Connection con;
	private ResultSet rs;
	private static final String INSERT_USER_AUTH_SQL = "INSERT INTO user_authorization" + "  (operation_authorization_id,user_type_id) OUTPUT Inserted.user_authorization_id VALUES " +
	        " (?,?);";
	private static final String UPDATE_USER_AUTH_SQL = "update user_authorization set operation_authorization_id = ? , user_type_id = ? where user_authorization_id = ?;";
	public UserAuthorizationDAOImpl(Connection con) {
		super(con);
		this.con=con;
	}

	@Override
	public int add(UserAuthorization userAuthorization) {
        boolean rowUpdated=false;
        int id=0;
        try ( PreparedStatement statement = con.prepareStatement(INSERT_USER_AUTH_SQL);){
            statement.setInt(1, userAuthorization.getOperationAuthorizationId());
            statement.setInt(2, userAuthorization.getUserTypeId());
            rs= statement.executeQuery();
            while(rs.next()) {
            	id=rs.getInt("user_authorization_id");
            }
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        if(rowUpdated) {
        	Log.logger(userAuthorization.getUserAuthorizationId()+ " userAuthorization added.");
        }
		return id;
	}

	@Override
	public void update( UserAuthorization userAuthorization) {
        boolean rowUpdated=false;
        try ( PreparedStatement statement = con.prepareStatement(UPDATE_USER_AUTH_SQL);) {
            statement.setInt(1, userAuthorization.getOperationAuthorizationId());
            statement.setInt(2, userAuthorization.getUserTypeId());
            statement.setInt(3, userAuthorization.getUserAuthorizationId());

            rowUpdated = statement.executeUpdate() > 0;
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        if(rowUpdated) {
        	Log.logger(userAuthorization.getOperationAuthorizationId() + " userAuthorization updated.");
        }
		
	}

	@Override
	public void delete(int id) {
		if(delete("user_Authorization",id)) { 
			Log.logger("userAuthorization deleted");
		}	
		
	}

	@Override
	public UserAuthorization findById(int id) {
		   rs =findById("user_authorization",id); 
		   UserAuthorization userAuthorization = convertToModel(rs);
		   return userAuthorization; 
	}

	@Override
	public List<UserAuthorization> getAll() {
		   rs =getAll("User_Authorization"); 
		   List<UserAuthorization> userAuthorizations= convertToList(rs);
		   return userAuthorizations; 
	}
	private UserAuthorization convertToModel(ResultSet rs)  {
		UserAuthorization  userAuthorization= new UserAuthorization ();
		try{
			while (rs.next()) {
				userAuthorization.setUserAuthorizationId(rs.getInt("user_authorization_id"));			
				userAuthorization.setOperationAuthorizationId(rs.getInt("operation_authorization_id"));
				userAuthorization.setUserTypeId(rs.getInt("user_type_id"));
			}
		}catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
		return userAuthorization;
	}
	private List<UserAuthorization> convertToList(ResultSet rs)  {
		List<UserAuthorization> userAuthorizationList= new ArrayList<UserAuthorization>();
		UserAuthorization  userAuthorization = new UserAuthorization();
		try{
			while (rs.next()) {
				userAuthorization.setUserAuthorizationId(rs.getInt("user_authorization_id"));			
				userAuthorization.setOperationAuthorizationId(rs.getInt("operation_authorization_id"));
				userAuthorization.setUserTypeId(rs.getInt("user_type_id"));
				userAuthorizationList.add(userAuthorization);
			}
		}catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
		return userAuthorizationList;
	}

}
