<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.revature.trms.servlet.Login"%> 
<%@page import="com.revature.trms.dao.ValidateDAO"%> 
<%@page import="com.revature.trms.model.Employee"%> 
<%@page import="com.revature.trms.dao.EmployeeDAO"%>


<%

try {
	Class.forName("org.postgresql.Driver");
} catch (ClassNotFoundException e) {
	e.printStackTrace();
}
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset='utf-8'>
			<meta http-equiv='X-UA-Compatible' content='IE=edge'>
			<title>All forms</title>
			<meta name='viewport' content='width=device-width, initial-scale=1'>
			<link rel='stylesheet' type='text/css' media='screen' href='styles/form.css'>
			<link rel="shortcut icon" href="resources/favicon.ico" type="image/x-icon" />
			<link rel="icon" href="resources/favicon.ico" type="image/ico" />
		</head>
<body>
<form action= "statusChange" method="post">
<%
	String name1 = (String) session.getAttribute("userName");
%>
<%
	String fullname = ValidateDAO.getEmployeeName(name1);
%>
<%
	String status = "";
%>
<%
	if (name1.equals("supervisor")) {
	status = "pending";
}
if (name1.equals("depthead")) {
	status = "Pending. Approved by Direct Supervisor";
}
 if(name1.equals("benco")){
	status = "Pending. Approved by Department Head";
}
%>
<header class="top">
        <a href="home.html">
            <img src="resources/revature.jpg" alt="Revature" style="width:175px;height:75px;">
        </a>
    </header>
    <div class="allform"> <center>
	<br><br><br>
	<h1>All forms for <%=fullname %></h1>
	<table border="1">
		 <tr> 
			<th>Employee</th>
	     <th>Event Type</th>
        <th>Event Location</th>
        <th>Examination Date</th>
        <th>Event Start Date</th>
        <th>Event Cost</th>
        <th>Grade Criteria</th>
		<th>Passing Grade</th>
		<th>Event Description</th> 
		<th>Status</th> 
		
        </tr>
		<%
			try {
				 connection = DriverManager.getConnection(DATABASEVALUES);
				 statement = connection.createStatement();
			String SELECT_EMPLOYEE_FORM = "select * from trms_employee_form";
			resultSet = statement.executeQuery(SELECT_EMPLOYEE_FORM);
			while (resultSet.next()) {
		%>
		<%if(resultSet.getString("status").equals(status)){ %>
		<tr>
			<td><%=resultSet.getString("empname")%></td>
			<td><%=resultSet.getString("eventtype")%></td>
			<td><%=resultSet.getString("emplocation")%></td>
			<td><%=resultSet.getString("examdate")%></td>
			<td><%=resultSet.getString("eventstartdate")%></td>
			<td><%=resultSet.getString("eventcost")%></td>
			<td><%=resultSet.getString("gradecriteria")%></td>
			<td><%=resultSet.getString("passinggrade")%></td>
			<td><%=resultSet.getString("eventdescription")%></td>
			<td><%=resultSet.getString("status")%></td><% } %>
		</tr>
		<%
			}
		connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		%>
	</table> </center> </div>


	<div class="form-content">
			<center>
			<h2>Take action: </h3> 
			<label for="nameAction">Name of employee:   </label>
			<select name="nameAction">  
			<%
			try {
				connection = DriverManager.getConnection(DATABASEVALUES);
				statement = connection.createStatement();
			String SELECT_EMPLOYEE_FORM = "select * from trms_employee_form";
			resultSet = statement.executeQuery(SELECT_EMPLOYEE_FORM);
			while (resultSet.next()) {
			%>
		 
			<%if(resultSet.getString("status").equals(status)){ %>
				<option> <%=resultSet.getString("empname")%></option> 
		<%
			}
			}
		connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		%>
				</select>
			<br><br>	<label for="statusAction">Change status:   </label>
				<select name="statusAction">  
					<option> Accept</option> 
					<option> Decline</option> 
					</select>
					<br>

			<input type="submit" class="button" value="Submit">
		</center>
		</form>
	</div>


</body>
</html>