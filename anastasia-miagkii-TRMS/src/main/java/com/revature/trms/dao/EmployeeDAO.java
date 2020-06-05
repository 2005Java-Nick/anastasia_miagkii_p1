package com.revature.trms.dao;


import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextPane;

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
			" set examdate = ?, eventstartdate = ?, eventtype = ?, eventdescription = ?, eventcost = ?, gradecriteria = ?, passinggrade = ?" +
			"where empusername = ?";



	//	UPDATE table_name
	//	SET column1 = value1, column2 = value2, ...
	//	WHERE condition;


	public int registerEmployeeForm () throws ClassNotFoundException, SQLException {

		url = "jdbc:postgresql://" + url + ":5432/" + db + "?";

		int result = 0;

		Class.forName("org.postgresql.Driver");

		try ( Connection connection = DriverManager.getConnection(DATABASEVALUES);

				PreparedStatement ps = connection.prepareStatement(UPDATE_EMPLOYEE_FORM)) {



			ps.setString(1, Employee.getExamDate());
			ps.setString(2, Employee.getEventDate());
			ps.setString(3, Employee.getEventType());
			ps.setString(4, Employee.getDescription());
			ps.setString(5, Employee.getCost());
			ps.setString(6, Employee.getGradeCriteria());
			ps.setString(7, Employee.getPassingGrade());
			ps.setString(8, Employee.getEmployeeUsername());


			System.out.println(ps);

			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return result;

	}

	public int registerEmployee() throws ClassNotFoundException, SQLException {

		//url = "jdbc:postgresql://" + url + ":5432/" + db + "?";


		int result = 0;

		Class.forName("org.postgresql.Driver");

		try ( Connection connection = DriverManager.getConnection(DATABASEVALUES);

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

	public static String viewEmployeeForm(String username) throws ClassNotFoundException, SQLException {

		//url = "jdbc:postgresql://" + url + ":5432/" + db + "?";


		String result = "";

		Class.forName("org.postgresql.Driver");
		String SELECT_EMPLOYEE_FORM = "SELECT * from trms_employee_form where empUsername = ?";

		try ( Connection connection = DriverManager.getConnection(DATABASEVALUES);

				PreparedStatement ps = connection.prepareStatement(SELECT_EMPLOYEE_FORM)) {

			ResultSet resultSet = null;

			try {

				ps.setString(1, username);

			} catch (SQLException e) {
				System.out.println("Oops! Unable to find any information!");
			} finally {
				try {
					resultSet = ps.executeQuery();



					while (resultSet.next()) {

						if(resultSet.getString("eventtype") == null) {

							result =  "<td colspan=\"8\"><center>No records found</center></td> </tr> " + 
									"     </table>";
							return result;
						}
						else {
							String eventtype = resultSet.getString("eventtype");
							String examdate = resultSet.getString("examdate") ;
							String eventstartdate = resultSet.getString("eventstartdate") ;
							String eventcost = resultSet.getString("eventcost");
							String gradecriteria = resultSet.getString("gradecriteria") ;
							String passinggrade = resultSet.getString("passinggrade") ;
							String emplocation = resultSet.getString("emplocation");
							String status = resultSet.getString("status") ;
							String description = resultSet.getString("eventdescription") ;
							String reimbursementbalance = resultSet.getString("reimbursementbalance") ;

							result =  "<tr> <td>"+ eventtype + "</td>" +
									"<td>"+ emplocation + "</td>" +
									"<td>"+ examdate + "</td>" +
									"<td>"+ eventstartdate + "</td>" +
									"<td>"+ eventcost + "</td>" +
									"<td>"+ gradecriteria + "</td>" +
									"<td>"+ passinggrade + "</td>" +	
									"<td bgcolor=\"red\" textcolor = \"white\">"+ status + "</td> </tr>" +
									"<tr> <td colspan=\"8\"><center>" + description + "</center></td> </tr> </table>"+
									"<h3><center>Your Reimbursement Balance is: " + reimbursementbalance +
									"</center> </h3>";

							return result;


						}


					}

					System.out.println(ps);

					ps.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}


			}
		}
		return result;
	}

	public static String viewAllForms(String empstatus) throws ClassNotFoundException, SQLException {

		//url = "jdbc:postgresql://" + url + ":5432/" + db + "?";


		String result = "";

		Class.forName("org.postgresql.Driver");
		String SELECT_EMPLOYEE_STATUS = "SELECT * from trms_employee_form where status = ?";

		try ( Connection connection = DriverManager.getConnection(DATABASEVALUES);

				PreparedStatement ps = connection.prepareStatement(SELECT_EMPLOYEE_STATUS)) {

			ResultSet resultSet = null;

			try {

				ps.setString(1, empstatus);

			} catch (SQLException e) {
				System.out.println("Oops! Unable to find any information!");
			} finally {
				try {
					resultSet = ps.executeQuery();



					while (resultSet.next()) {

						if(resultSet.getString("eventtype") == "NULL") {

							result =  "<td colspan=\"8\"> <center> No records found </center></td> </tr> " + 
									"     </table>";
							return result;
						}
						else {




							result = " <tr><td>"+ resultSet.getString("eventtype") + "</td>" +
									"<td>"+ resultSet.getString("emplocation") + "</td>" +
									"<td>"+ resultSet.getString("examdate") + "</td>" +
									"<td>"+ resultSet.getString("eventstartdate") + "</td>" +
									"<td>"+ resultSet.getString("eventcost") + "</td>" +
									"<td>"+ resultSet.getString("gradecriteria") + "</td>" +
									"<td>"+ resultSet.getString("passinggrade") + "</td>";	
								//	+ "<td>"+ resultSet.getString("status") + "</td> </tr>"; 
								if(resultSet.getString("status").equals("Approved")) {
									result = result + "<td bg color = \"green\" color = \"black\">"+ resultSet.getString("status") + "</td> </tr>"; 
								}
								if(resultSet.getString("status").equals("Declined")) {
									result = result + "<td bg color = \"red\" color = \"black\">"+ resultSet.getString("status") + "</td> </tr>"; 
								}
								else {
									result = result + "<td bg color = \"yellow\" color = \"black\">"+ resultSet.getString("status") + "</td> </tr>";
								}




						}


					}

					System.out.println(ps);

					ps.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}


			}
		}
		return result;
	}


	public int updateEmployeeFormStatus (String name, String status) throws ClassNotFoundException, SQLException {

		String UPDATE_EMPLOYEE_FORM_STATUS = "UPDATE trms_employee_form" + 
				" set status = ? " +
				"where empname = ?";


		int result = 0;

		Class.forName("org.postgresql.Driver");

		try ( Connection connection = DriverManager.getConnection(DATABASEVALUES);

				PreparedStatement ps = connection.prepareStatement(UPDATE_EMPLOYEE_FORM_STATUS)) {


			ps.setString(1, status);
			ps.setString(2, name);


			System.out.println(ps);

			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return result;

	}

	public int updateEmployeeBalance (String empname, double balance) throws ClassNotFoundException, SQLException {

		String UPDATE_EMPLOYEE_BALANCE = "UPDATE trms_employee_form" + 
				" set reimbursementbalance = ? " +
				"where empname = ?";

	int result = 0;

	Class.forName("org.postgresql.Driver");

	try ( Connection connection = DriverManager.getConnection(DATABASEVALUES);

			PreparedStatement ps = connection.prepareStatement(UPDATE_EMPLOYEE_BALANCE)) {

		System.out.println("Updating " + empname + " balance . . .");
		String sqlbalance = Double.toString(balance);
		ps.setString(1, sqlbalance);
		ps.setString(2, empname);
		System.out.println("Setting balance . . . Balance is " + balance);
		//System.out.println("SQL String Balance " + sqlbalance);

		System.out.println(ps);

		result = ps.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}


	return result;

}




	public double setBalance(String empname) throws ClassNotFoundException, SQLException {

		String result = "";

		Class.forName("org.postgresql.Driver");
		String SELECT_EMPLOYEE = "SELECT * from trms_employee_form where empname = ?";

		double balance = 0;
		try ( Connection connection = DriverManager.getConnection(DATABASEVALUES);

				PreparedStatement ps = connection.prepareStatement(SELECT_EMPLOYEE)) {

			ResultSet resultSet = null;	

			try {

				ps.setString(1, empname);

			} catch (SQLException e) {
				System.out.println("Oops! Unable to find any information!");
			} finally {
				try {
					resultSet = ps.executeQuery();

					while (resultSet.next()) {
						
						double eventcost = Double.valueOf(resultSet.getString("eventcost"));

						if(resultSet.getString("eventtype").equals("University Courses")) {
							balance = eventcost * 0.8;
							//System.out.println("Balance in if statement" + balance);

						}
						if(resultSet.getString("eventtype").equals("Seminar")) {
							balance = eventcost * 0.6;
							//System.out.println("Balance in if statement" + balance);

						}
						if(resultSet.getString("eventtype").equals("Certification Preparation Class")) {
							balance = eventcost * 0.75;
							//System.out.println("Balance in if statement" + balance);

						}
						if(resultSet.getString("eventtype").equals("Certification")) {
							balance = eventcost;
							//System.out.println("Balance in if statement" + balance);

						}
						if(resultSet.getString("eventtype").equals("Technical Training")) {
							balance = eventcost * 0.9;
							//System.out.println("Balance in if statement" + balance);

						}
						if(resultSet.getString("eventtype").equals("Other")) {
							balance = eventcost * 0.3;
							//System.out.println("Balance in if statement" + balance);

						}

					}
					
					//System.out.println(balance);
					System.out.println(ps);

				//	ps.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}



			}
		}
		return balance;
	}






}

