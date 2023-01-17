package com.SupplyChainManagementProject.Core.DbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.SupplyChainManagementProject.Core.Helper.Exceptions;


public class PostgreSqlDbConnection {
	private static Connection connection=null;
	public static Connection getConnection(){
		if(connection==null) {
			try {
				Class.forName("org.postgresql.Driver");
				connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/SupplyChainManagement", "postgres","12345");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Exceptions.printSQLException(e);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return connection;	
	}
}
