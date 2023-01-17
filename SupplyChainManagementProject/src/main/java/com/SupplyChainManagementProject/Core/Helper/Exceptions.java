package com.SupplyChainManagementProject.Core.Helper;

import java.sql.SQLException;

public class Exceptions {
	
    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    //ClassNotFoundException
    public static void printClassNotFoundException(ClassNotFoundException ex) {
            if (ex instanceof ClassNotFoundException) {
                ex.printStackTrace(System.err);
                System.err.println("LocalizedMessage: " + ((ClassNotFoundException) ex).getLocalizedMessage());
                System.err.println("Exception: " + ((ClassNotFoundException) ex).getException());
                System.err.println("Message: " + ex.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
    }

}
