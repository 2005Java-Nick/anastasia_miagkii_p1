package com.revature.trms.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.revature.trms.dao.ValidateDAO;
import com.revature.trms.model.Employee;

import java.sql.*;

public class Login extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
		dispatcher.forward(request, response);
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		System.out.println("Login servlet was called!");
		
		HttpSession session = request.getSession();
		String username = request.getParameter("userName");
		session.setAttribute("userName", username);
	
//		RequestDispatcher rd = request.getRequestDispatcher("login.html");
//		rd.forward(request, response);

		response.setContentType("text/html;charset=UTF-8");
		//PrintWriter out = response.getWriter();


		String pass = request.getParameter("userPass");
		

		//MENU FOR EMPLOYEE
		if(ValidateDAO.checkUser(username, pass, "employee"))
		{

			try {
				
				Employee.setEmployeeUsername(username);
				
				//set session
				session.setAttribute("userName",username);
				String sessionname = (String) session.getAttribute("userName");
				String fullName = ValidateDAO.getEmployeeName(sessionname);
				request.setAttribute("name", fullName); 
			//	request.getRequestDispatcher("welcome.jsp").forward(request, response);
				request.getRequestDispatcher("welcome.jsp").forward(request, response);
				//response.sendRedirect("welcome.jsp");
				
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		//MENU FOR MANAGERS
		if(ValidateDAO.checkUser(username, pass, "high"))
		{
//			RequestDispatcher rs = request.getRequestDispatcher("welcomeadmin.jsp");
//			rs.forward(request, response);
			
			//set session
			session.setAttribute("userName",username);
			//response.sendRedirect("welcomeadmin.jsp");
			request.getRequestDispatcher("welcomeadmin.jsp").forward(request, response);
		}
		
		//IF USERNAME OR PASSWORD IS INCORRECT
		else
		{
			//session.invalidate();
			RequestDispatcher rs = request.getRequestDispatcher("incorrectlogin.html");
			rs.include(request, response);
		}
	}  
}