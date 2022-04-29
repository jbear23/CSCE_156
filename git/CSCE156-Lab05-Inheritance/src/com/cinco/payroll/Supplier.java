package com.cinco.payroll;

public class Supplier implements Payable {
	String companyName;
	double amountDue;
	
	
	
	public Supplier(String companyName, double amountDue) {
		super();
		this.companyName = companyName;
		this.amountDue = amountDue;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public double getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}

	public double getNetPay() {		
		return amountDue;
	}
	
}
