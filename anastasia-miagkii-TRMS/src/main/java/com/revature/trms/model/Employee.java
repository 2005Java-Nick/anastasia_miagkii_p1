package com.revature.trms.model;

public class Employee {

	private static String employeeFullname;
	private static String employeeUsername;
	private static String employeePassword;
	private static String employeeEmail;
	private static String employeeLocation;
	private static String eventType;
	private static String examDate;
	
	
	public static String getExamDate() {
		return examDate;
	}
	public static void setExamDate(String examDate) {
		Employee.examDate = examDate;
	}
	private static String eventDate;
	private static String cost;
	public static String getPassingGrade() {
		return passingGrade;
	}
	public static void setPassingGrade(String passingGrade) {
		Employee.passingGrade = passingGrade;
	}
	private static String gradeCriteria;
	private static String description;
	private static String passingGrade;
	
	public static String getEventType() {
		return eventType;
	}
	public static void setEventType(String eventType) {
		Employee.eventType = eventType;
	}
	public static String getEventDate() {
		return eventDate;
	}
	public static void setEventDate(String eventDate) {
		Employee.eventDate = eventDate;
	}
	public static String getCost() {
		return cost;
	}
	public static void setCost(String cost) {
		Employee.cost = cost;
	}
	public static String getGradeCriteria() {
		return gradeCriteria;
	}
	public static void setGradeCriteria(String gradeCriteria) {
		Employee.gradeCriteria = gradeCriteria;
	}
	public static String getDescription() {
		return description;
	}
	public static void setDescription(String description) {
		Employee.description = description;
	}
	
	public static String getEmployeeFullname() {
		return employeeFullname;
	}
	public static void setEmployeeFullname(String employeeFullname) {
		Employee.employeeFullname = employeeFullname;
	}
	public static String getEmployeeUsername() {
		return employeeUsername;
	}
	public static void setEmployeeUsername(String employeeUsername) {
		Employee.employeeUsername = employeeUsername;
	}
	public static String getEmployeePassword() {
		return employeePassword;
	}
	public static void setEmployeePassword(String employeePassword) {
		Employee.employeePassword = employeePassword;
	}
	public static String getEmployeeEmail() {
		return employeeEmail;
	}
	public static void setEmployeeEmail(String employeeEmail) {
		Employee.employeeEmail = employeeEmail;
	}
	public static String getEmployeeLocation() {
		return employeeLocation;
	}
	public static void setEmployeeLocation(String employeeLocation) {
		Employee.employeeLocation = employeeLocation;
	}

	
	
	
	
}
