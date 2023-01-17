package com.SupplyChainManagementProject.Core.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.SupplyChainManagementProject.Core.Helper.Exceptions;
import com.SupplyChainManagementProject.Core.Model.GenericModel;

public abstract class GenericDAOImpl <T extends GenericModel> implements IGenericDAO<T> {
	private Connection con;
	private ResultSet rs= null;
	public GenericDAOImpl(Connection con) {
		super();
		this.con = con;
	}
    protected ResultSet findById(String tableName, Integer id){
        try(PreparedStatement pStmt= con.prepareStatement("SELECT * FROM "+ tableName+ "WHERE "+tableName+"_id = ?;");) {
            pStmt.setInt(1, id);
            rs = pStmt.executeQuery();
        }catch (SQLException ex) {
        	Exceptions.printSQLException(ex);
        }finally{
            return rs;
        }
    }
    protected ResultSet getAll(String tableName){
        try (PreparedStatement pStmt= con.prepareStatement("SELECT * FROM "+ tableName+";");){
            rs = pStmt.executeQuery();
        }catch (SQLException ex) {
        	Exceptions.printSQLException(ex);
        }finally{
            return rs;
        }
    }
    protected boolean delete(String tableName,Integer id){
    	boolean isDeleted = false;
        try (PreparedStatement pStmt= con.prepareStatement("DELETE FROM "+tableName+" WHERE "+tableName+"_id = ?;");){
            pStmt.setInt(1, id);
            isDeleted = pStmt.executeUpdate()>0;
        }catch (SQLException ex) {
        	Exceptions.printSQLException(ex);
        }finally{
            return isDeleted;
        }
    }
}
