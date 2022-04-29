package com.cinco.payroll;

public class SalaryEmployee extends Employee {
	double baseAnnualSalary;
	
	public double getBaseAnnualSalary() {
		return baseAnnualSalary;
	}
	
	public void setBaseAnnualSalary(double baseAnnualSalary) {
		this.baseAnnualSalary = baseAnnualSalary;
	}
	
	public double getNetPay() {
		return baseAnnualSalary - getTaxes() + 100;
	}
	
	public double getGrossPay() {
		double grossPay = getBaseAnnualSalary();
		
		return grossPay / 52.0;
	}
	
	public String getType() {
		return "Salary";
	}
	
	public double getTaxes() {
		return 0.20 * baseAnnualSalary;
	}
	
	public SalaryEmployee(String employeeId, String employeeFirstName, String employeeLastName, String title,
			double baseAnnualSalary) {
		super(employeeId, employeeFirstName, employeeLastName, title);
		this.baseAnnualSalary = baseAnnualSalary;
	}
	
}