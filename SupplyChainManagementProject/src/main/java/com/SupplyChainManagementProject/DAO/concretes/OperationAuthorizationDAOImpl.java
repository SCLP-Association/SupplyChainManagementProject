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
import com.SupplyChainManagementProject.DAO.abstracts.IOperationAuthorizationDAO;
import com.SupplyChainManagementProject.Model.OperationAuthorization;


public class OperationAuthorizationDAOImpl extends GenericDAOImpl<OperationAuthorization> implements IOperationAuthorizationDAO{
	private Connection con;
	private ResultSet rs;
	private static final String INSERT_OPER_AUTH_SQL = "INSERT INTO Operation_Authorization" + "  (definition) OUTPUT Inserted.operation_authorization_id VALUES " +
	        " (?);";
	private static final String UPDATE_OPER_AUTH_SQL = "update Operation_Authorization set definition = ? where operation_authorization_id = ?;";
	public OperationAuthorizationDAOImpl(Connection con) {
		super(con);
		this.con=con;
	}

	@Override
	public int add(OperationAuthorization operationAuthorization) {
		// TODO Auto-generated method stub
        boolean rowUpdated=false;
        int id=0;
        try ( PreparedStatement statement = con.prepareStatement(INSERT_OPER_AUTH_SQL);){
            statement.setString(1, operationAuthorization.getDefinition());
            rs= statement.executeQuery();
            while(rs.next()) {
            	id=rs.getInt("operation_authorization_id");
            }
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        if(rowUpdated) {
        	Log.logger(operationAuthorization.getDefinition()+ " operationAuthorization added.");
        }
        return id;
	}

	@Override
	public void update( OperationAuthorization operationAuthorization) {
		// TODO Auto-generated method stub
        boolean rowUpdated=false;
        try ( PreparedStatement statement = con.prepareStatement(UPDATE_OPER_AUTH_SQL);) {
            statement.setString(1, operationAuthorization.getDefinition());
            statement.setInt(2, operationAuthorization.getOperationAuthorizationId());

            rowUpdated = statement.executeUpdate() > 0;
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        if(rowUpdated) {
        	Log.logger(operationAuthorization.getDefinition() + " operationAuthorization updated.");
        }
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		if(delete("operation_authorization",id)) { 
			Log.logger("operationAuthorization deleted");
		}
	}

	@Override
	public OperationAuthorization findById(int id) {
		// TODO Auto-generated method stub
		   rs =findById("operation_authorization",id); 
		   OperationAuthorization operationAuthorization = convertToModel(rs);
		   return operationAuthorization; 
	}

	@Override
	public List<OperationAuthorization> getAll() {
		// TODO Auto-generated method stub
		   rs =getAll("operation_Authorization"); 
		   List<OperationAuthorization> operationAuthorizations= convertToList(rs);
		   return operationAuthorizations; 
	}
	private OperationAuthorization convertToModel(ResultSet rs)  {
		OperationAuthorization  operationAuthorization= new OperationAuthorization ();
		try{
			while (rs.next()) {
				operationAuthorization.setOperationAuthorizationId(rs.getInt("operation_authorization_id"));
				operationAuthorization.setDefinition(rs.getString("definition"));
			}
		}catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
		return operationAuthorization;
	}
	private List<OperationAuthorization> convertToList(ResultSet rs)  {
		List<OperationAuthorization> operationAuthorizationList= new ArrayList<OperationAuthorization>();
		OperationAuthorization  operationAuthorization= new OperationAuthorization ();
		try{
			while (rs.next()) {
				operationAuthorization.setOperationAuthorizationId(rs.getInt("operation_authorization_id"));
				operationAuthorization.setDefinition(rs.getString("definition"));
				operationAuthorizationList.add(operationAuthorization);
			}
		}catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
		return operationAuthorizationList;
	}

}
