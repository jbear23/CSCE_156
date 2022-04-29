package com.cinco.payroll;

public abstract class Employee implements Payable {
	String employeeId;
	String employeeFirstName;
	String employeeLastName;
	String title;
	String type;
	
	public Employee(String employeeId, String employeeFirstName, String employeeLastName, String title) {
		this.employeeId = employeeId;
		this.employeeFirstName = employeeFirstName;
		this.employeeLastName = employeeLastName;
		this.title = title;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeFirstName() {
		return employeeFirstName;
	}

	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}

	public String getEmployeeLastName() {
		return employeeLastName;
	}

	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public double getNetpay() {
		return getGrossPay() - getNetPay();
	}

	public abstract String getType();
	
	public abstract double getGrossPay();
	
	public abstract double getTaxes();

	public String getName() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%s, %s\n", getEmployeeLastName(), getEmployeeFirstName()));
		
		return sb.toString();
	}
	
}