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
import com.SupplyChainManagementProject.DAO.abstracts.IUserUserTypeDAO;
import com.SupplyChainManagementProject.Model.UserUserType;

public class UserUserTypeDAOImpl extends GenericDAOImpl<UserUserType> implements IUserUserTypeDAO {
	private Connection con;
	private ResultSet rs;
	private static final String INSERT_U_USERTYPE_SQL = "INSERT INTO user_user_type" + "  (user_id,user_type_id) OUTPUT Inserted.user_user_type_id VALUES " +
	        " (?,?);";
	private static final String UPDATE_U_USERTYPE_SQL = "update user_user_type set user_id = ? , user_type_id = ? where user_user_type_id = ?;";
	private static final String FINDBYUSERID_SQL ="SELECT user_type_id FROM  user_user_type WHERE user_id = ?;";
	public UserUserTypeDAOImpl(Connection con) {
		super(con);
		this.con=con;
	}

	@Override
	public int add(UserUserType userUserType) {
        boolean rowUpdated=false;
        int id=0;
        try ( PreparedStatement statement = con.prepareStatement(INSERT_U_USERTYPE_SQL);){
            statement.setInt(1, userUserType.getUserId());
            statement.setInt(2, userUserType.getUserTypeId());
            rs= statement.executeQuery();
            while(rs.next()) {
            	id=rs.getInt("user_user_type_id");
            }
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        if(rowUpdated) {
        	Log.logger(userUserType.getUserUserTypeId()+ " userUserType added.");
        }
        return id;
		
	}

	@Override
	public void update( UserUserType userUserType) {
        boolean rowUpdated=false;
        try ( PreparedStatement statement = con.prepareStatement(UPDATE_U_USERTYPE_SQL);) {
            statement.setInt(1, userUserType.getUserId());
            statement.setInt(2, userUserType.getUserTypeId());
            statement.setInt(3, userUserType.getUserUserTypeId());

            rowUpdated = statement.executeUpdate() > 0;
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        if(rowUpdated) {
        	Log.logger(userUserType.getUserUserTypeId() + " userUserType updated.");
        }
		
	}

	@Override
	public void delete(int id) {
		if(delete("user_user_type",id)) { 
			Log.logger("userusertype deleted");
		}			
	}

	@Override
	public UserUserType findById(int id) {
		   rs =findById("user_user_type",id); 
		   UserUserType userUserType = convertToModel(rs);
		   return userUserType; 
	}

	@Override
	public List<UserUserType> getAll() {
		   rs =getAll("user_user_type"); 
		   List<UserUserType> userUserTypes= convertToList(rs);
		   return userUserTypes; 
	}
	private UserUserType convertToModel(ResultSet rs)  {
		UserUserType  userUserType= new UserUserType ();
		try{
			while (rs.next()) {
				userUserType.setUserUserTypeId(rs.getInt("user_user_type_id"));			
				userUserType.setUserId(rs.getInt("user_id"));
				userUserType.setUserTypeId(rs.getInt("user_type_id"));
			}
		}catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
		return userUserType;
	}
	private List<UserUserType> convertToList(ResultSet rs)  {
		List<UserUserType> userUserTypeList= new ArrayList<UserUserType>();
		UserUserType  userUserType = new UserUserType();
		try{
			while (rs.next()) {
				userUserType.setUserUserTypeId(rs.getInt("user_user_type_id"));			
				userUserType.setUserId(rs.getInt("user_id"));
				userUserType.setUserTypeId(rs.getInt("user_type_id"));
				userUserTypeList.add(userUserType);
			}
		}catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
		return userUserTypeList;
	}

	@Override
	public UserUserType findByUserId(int id) {
		UserUserType userUserType=new UserUserType();
        try ( PreparedStatement statement = con.prepareStatement(FINDBYUSERID_SQL);){
            statement.setInt(1, id);
            rs= statement.executeQuery();
            userUserType = convertToModel(rs);
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        return userUserType;
	}

}
