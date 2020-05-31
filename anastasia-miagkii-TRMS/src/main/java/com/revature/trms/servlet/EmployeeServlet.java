package com.revature.trms.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.trms.dao.EmployeeDAO;
import com.revature.trms.model.Employee;

/**
 * Servlet implementation class EmployeeServlet
 */

@WebServlet("/register")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmployeeDAO employeeDao = new EmployeeDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("signup.html");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fullName = request.getParameter("employeeName");
		String userName = request.getParameter("employeeUsername");
		String password = request.getParameter("employeePassword");
		String email = request.getParameter("employeeEmail");
		String location = request.getParameter("employeeLocation");
		
		//Employee employee = new Employee();
		
		Employee.setEmployeeFullname(fullName);
		Employee.setEmployeeUsername(userName);
		Employee.setEmployeePassword(password);
		Employee.setEmployeeEmail(email);
		Employee.setEmployeeLocation(location);
		
		try {
			employeeDao.registerEmployee();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.html");
			dispatcher.forward(request, response);
			System.out.println("Unable to register employee");
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("success.html");
		dispatcher.forward(request, response);
		
		//response.sendRedirect("");
		
	}

}
