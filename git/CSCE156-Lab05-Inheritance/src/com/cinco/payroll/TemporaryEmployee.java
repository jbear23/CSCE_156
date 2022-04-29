package com.cinco.payroll;

public class TemporaryEmployee extends HourlyEmployee {

	public double getTaxes() {
		return 0;
	}
	
	public String getType() {
		return "Temporary";
	}

	public TemporaryEmployee(String employeeId, String employeeFirstName, String employeeLastName, String title,
			double hourlyPayRate, double hoursWorked) {
		super(employeeId, employeeFirstName, employeeLastName, title, hourlyPayRate, hoursWorked);
	}
	
}
