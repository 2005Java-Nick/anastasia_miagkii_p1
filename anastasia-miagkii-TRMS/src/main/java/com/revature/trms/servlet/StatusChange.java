package com.revature.trms.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.trms.dao.EmployeeDAO;

public class StatusChange extends HttpServlet {

	private EmployeeDAO employeeDao = new EmployeeDAO();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		System.out.println("Status Change servlet was called!");
		
		HttpSession session = request.getSession();
		//String username = request.getParameter("userName");
		session.getAttribute("userName");
		
		
		String username = (String) session.getAttribute("userName");
		String empname = request.getParameter("nameAction");
		String status = request.getParameter("statusAction");
		//String eventDate = request.getParameter("eventDate");
		
		
		try {
			if(status.equals("Decline")) {
				employeeDao.updateEmployeeFormStatus(empname, "Declined");
			}
			if(status.equals("Accept")) {
				if(username.equals("supervisor")) {
					employeeDao.updateEmployeeFormStatus(empname, "Pending. Approved by Direct Supervisor");
				}
				if(username.equals("depthead")) {
					employeeDao.updateEmployeeFormStatus(empname, "Pending. Approved by Department Head");
				}
				if(username.equals("benco")) {
					employeeDao.updateEmployeeFormStatus(empname, "Approved");
					double balance = employeeDao.setBalance(empname);
					employeeDao.updateEmployeeBalance(empname, balance);
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Unable to register employee form");
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("statuschange.html");
		dispatcher.forward(request, response);
		
		
	}
	
	
}