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
import com.SupplyChainManagementProject.DAO.abstracts.IUserTypeDAO;
import com.SupplyChainManagementProject.Model.UserType;

public class UserTypeDAOImpl extends GenericDAOImpl<UserType> implements IUserTypeDAO {
	private Connection con;
	private ResultSet rs;
	private static final String INSERT_USERTYPE_SQL = "INSERT INTO user_type" + "  (definition) OUTPUT Inserted.user_type_id VALUES " +
	        " (?);";
	private static final String UPDATE_USERTYPE_SQL = "update user_type set definition = ? where user_type_id = ?;";
	public UserTypeDAOImpl(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
		this.con=con;
	}

	@Override
	public int add(UserType userType) {
		// TODO Auto-generated method stub
        boolean rowUpdated=false;
        int id=0;
        try ( PreparedStatement statement = con.prepareStatement(INSERT_USERTYPE_SQL);){
            statement.setString(1, userType.getDefinition());
            rs= statement.executeQuery();
            while(rs.next()) {
            	id=rs.getInt("user_type_id");
            }
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        if(rowUpdated) {
        	Log.logger(userType.getDefinition()+ " user type added.");
        }
        return id;
	}

	@Override
	public void update( UserType userType) {
		// TODO Auto-generated method stub
        boolean rowUpdated=false;
        try ( PreparedStatement statement = con.prepareStatement(UPDATE_USERTYPE_SQL);) {
            statement.setString(1, userType.getDefinition());
            statement.setInt(2, userType.getUserTypeId());

            rowUpdated = statement.executeUpdate() > 0;
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        if(rowUpdated) {
        	Log.logger(userType.getDefinition() + " usertype updated.");
        }
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		if(delete("User_Type",id)) { 
			Log.logger("usertype deleted");
		}
	}

	@Override
	public UserType findById(int id) {
		// TODO Auto-generated method stub
		   rs =findById("USER_TYPE",id); 
		   UserType userType = convertToModel(rs);
		   return userType; 
	}

	@Override
	public List<UserType> getAll() {
		// TODO Auto-generated method stub
		   rs =getAll("USER_TYPE"); 
		   List<UserType> userTypes= convertToList(rs);
		   return userTypes; 
	}
	private UserType convertToModel(ResultSet rs)  {
		 UserType  userType= new UserType ();
		try{
			while (rs.next()) {
				userType.setUserTypeId(rs.getInt("user_type_id"));
				userType.setDefinition(rs.getString("definition"));
			}
		}catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
		return userType;
	}
	private List<UserType> convertToList(ResultSet rs)  {
		List<UserType> userTypeList= new ArrayList<UserType>();
		 UserType  userType= new UserType ();
		try{
			while (rs.next()) {
				userType.setUserTypeId(rs.getInt("user_type_id"));
				userType.setDefinition(rs.getString("definition"));
				userTypeList.add(userType);
			}
		}catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
		return userTypeList;
	}

}
