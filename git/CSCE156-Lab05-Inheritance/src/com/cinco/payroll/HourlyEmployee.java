package com.cinco.payroll;

public class HourlyEmployee extends Employee {
	double hourlyPayRate;
	double hoursWorked;
	
	public double getHourlyPayRate() {
		return hourlyPayRate;
	}
	
	public void setHourlyPayRate(double hourlyPayRate) {
		this.hourlyPayRate = hourlyPayRate;
	}
	
	public double getHoursWorked() {
		return hoursWorked;
	}
	
	public void setHoursWorked(double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}
	
	public HourlyEmployee(String employeeId, String employeeFirstName, String employeeLastName, String title,
			double hourlyPayRate, double hoursWorked) {
		super(employeeId, employeeFirstName, employeeLastName, title);
		this.hourlyPayRate = hourlyPayRate;
		this.hoursWorked = hoursWorked;
	}

	public double getNetPay() {
		return getGrossPay() - getTaxes();
	}

	public String getType() {
		return "Hourly";
	}

	@Override
	public double getGrossPay() {
		return hourlyPayRate * hoursWorked;
	}

	public double getTaxes() {
		return 0;
	}
	
}
