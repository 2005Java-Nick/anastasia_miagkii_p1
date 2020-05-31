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


@WebServlet("/apply")
public class EmployeeFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmployeeDAO employeeDao = new EmployeeDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeFormServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("apply.html");
		dispatcher.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String eventType = request.getParameter("eventType");
		String submissionDate = request.getParameter("submissionDate");
		String eventDate = request.getParameter("eventDate");
		String cost = request.getParameter("cost");
		String gradeCriteria = request.getParameter("gradeCriteria");
		String description = request.getParameter("description");
		
		//Employee employee = null;
		

		
		Employee.setEventType(eventType);
		Employee.setSubmissionDate(submissionDate);
		Employee.setEventDate(eventDate);
		Employee.setCost(cost);
		Employee.setGradeCriteria(gradeCriteria);
		Employee.setDescription(description);
		
		try {
			employeeDao.registerEmployeeForm();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Unable to register employee form");
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("successform.html");
		dispatcher.forward(request, response);
		
		//response.sendRedirect("");
		
	}

}
