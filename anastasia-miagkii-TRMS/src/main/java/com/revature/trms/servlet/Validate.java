package com.revature.trms.servlet;

import java.sql.*;


public class Validate {
	
    public static boolean checkUser(String username, String pass, String level) 
    {
        boolean st =false;
        try {

            //loading drivers for mysql
        	Class.forName("org.postgresql.Driver");

            //creating connection with the database
        	Connection con = DriverManager.getConnection("","","");
        	PreparedStatement ps = con.prepareStatement("select * from trms_lvl_employee where empUsername=? and empPassword=? and empLevel =?");
            ps.setString(1, username);
            ps.setString(2, pass);
            ps.setString(3, level);
            ResultSet rs = ps.executeQuery();
            st = rs.next();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return st;                 
    }   
    
    
public static String getEmployeeName(String username) throws SQLException, ClassNotFoundException {
	Class.forName("org.postgresql.Driver");
	Connection conn = DriverManager.getConnection("","","");
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		String employeeName = "";
			try {
				stmt = conn.prepareStatement("select * from trms_new_employee where empUsername = ?");
				stmt.setString(1, username);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					resultSet = stmt.executeQuery();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			try {
				while (resultSet.next()) {
					employeeName = resultSet.getString("empName");
					
				}
				try {
					conn.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
			} catch (SQLException e) {
		
		e.printStackTrace();
	}
	
		}
			return employeeName;	
	}

    
}