package com.revature.trms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.trms.model.Employee;

public class EmployeeDAO {
	
	private String url = System.getenv("soft_url");
	private String db = System.getenv("soft_dbname");
	private String username = System.getenv("soft_username");
	private String password = System.getenv("soft_password");
	
	String INSERT_EMPLOYEE = "insert into trms_new_employee" +
			" (empName, empUsername, empPassword, empEmail, empLocation) values" +
			" ( ?, ?, ?, ?, ?);";
	
	String UPDATE_EMPLOYEE_FORM = "UPDATE trms_employee_form" + 
			" set submissiondate = ?, eventstartdate = ?, eventtype = ?, eventdescription = ?, eventcost = ?, gradecriteria = ?" +
					"where empusername = ?";
	
//	UPDATE table_name
//	SET column1 = value1, column2 = value2, ...
//	WHERE condition;
	

	public int registerEmployeeForm () throws ClassNotFoundException, SQLException {
		
		url = "jdbc:postgresql://" + url + ":5432/" + db + "?";
		
		int result = 0;
		
		Class.forName("org.postgresql.Driver");
		
		try ( Connection connection = DriverManager.getConnection(url, username, password);
				
				PreparedStatement ps = connection.prepareStatement(UPDATE_EMPLOYEE_FORM)) {
			
			ps.setString(1, Employee.getSubmissionDate());
			ps.setString(2, Employee.getEventDate());
			ps.setString(3, Employee.getEventType());
			ps.setString(4, Employee.getDescription());
			ps.setString(5, Employee.getCost());
			ps.setString(6, Employee.getGradeCriteria());
			ps.setString(7, Employee.getEmployeeUsername());
			
			System.out.println(ps);
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
		
	}

	public int registerEmployee() throws ClassNotFoundException, SQLException {
		
		//url = "jdbc:postgresql://" + url + ":5432/" + db + "?";
		
		String INSERT_EMPLOYEE = "insert into trms_new_employee" +
		" (empName, empUsername, empPassword, empEmail, empLocation) values" +
		" ( ?, ?, ?, ?, ?);";
		
		int result = 0;
		
		Class.forName("org.postgresql.Driver");
		
		try ( Connection connection = DriverManager.getConnection(url, username, password);
				PreparedStatement ps = connection.prepareStatement(INSERT_EMPLOYEE)) {
			
			ps.setString(1, Employee.getEmployeeFullname());
			ps.setString(2, Employee.getEmployeeUsername());
			ps.setString(3, Employee.getEmployeePassword());
			ps.setString(4, Employee.getEmployeeEmail());
			ps.setString(5, Employee.getEmployeeLocation());
			
			System.out.println(ps);
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
		
	}
}
