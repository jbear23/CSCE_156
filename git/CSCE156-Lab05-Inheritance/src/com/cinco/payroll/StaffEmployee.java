package com.cinco.payroll;

public class StaffEmployee extends HourlyEmployee {
	double taxRate = 0.15;
	
	public double getTaxes() {
		return taxRate * hourlyPayRate;
	}
	
	public String getType() {
		return "Staff";
	}

	public StaffEmployee(String employeeId, String employeeFirstName, String employeeLastName, String title,
			double hourlyPayRate, double hoursWorked) {
		super(employeeId, employeeFirstName, employeeLastName, title, hourlyPayRate, hoursWorked);
	}
	
}
