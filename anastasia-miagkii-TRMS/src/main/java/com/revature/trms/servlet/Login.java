package com.revature.trms.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Login extends HttpServlet {
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Validate validate = new Validate();
        
        String username = request.getParameter("userName");
        String pass = request.getParameter("userPass");
        
        
        try {
			String fullName = Validate.getEmployeeName(username);
			request.setAttribute("name", fullName); 
			request.getRequestDispatcher("welcome.jsp").forward(request, response); 
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        if(Validate.checkUser(username, pass, "employee"))
        {
            RequestDispatcher rs = request.getRequestDispatcher("welcome.jsp");
            rs.forward(request, response);
            
        }
        if(Validate.checkUser(username, pass, "high"))
        {
            RequestDispatcher rs = request.getRequestDispatcher("welcomeadmin.jsp");
            rs.forward(request, response);
            
        }
        else
        {
           RequestDispatcher rs = request.getRequestDispatcher("incorrectlogin.html");
           rs.include(request, response);
        }
    }  
}