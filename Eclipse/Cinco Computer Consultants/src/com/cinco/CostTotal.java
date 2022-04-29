package com.cinco;

//CostTotal will be the invoices TotalCost, with functions to handle how to represent and handle invoices for the end user
//Due it containing no "unique" data, it will never be permanent stored with other data, instead generated as is called on.
public class CostTotal {
	
	private Cost[] Listings;
	private Double ComplianceFee;
	
	public CostTotal(Cost[] Listings, Double ComplianceFee) {
		Listings = Listings;
		ComplianceFee = ComplianceFee;
	}
	
	public Cost[] getListings() {
		return Listings;
	}
	
	public Double getComplianceFee() {
		return ComplianceFee;
	}
	
	public Double getFees() {
		double TotalFees = 0;
		for(Cost Element: Listings) {
			TotalFees += Element.getFee();
		}
		
		return TotalFees;
	}
	
	public Double getTax() {
		double TotalTaxes = 0;
		for(Cost Element: Listings) {
			TotalTaxes += Element.getTaxes();
		}
		return TotalTaxes;
	}
	
	public Double getPrice() {
		double TotalPrice = 0;
		for(Cost Element: Listings) {
			TotalPrice += Element.getPrice();
		}
		return TotalPrice;
	}
	
	public Double getTotalCost() {
		return this.getFees() + this.getTax() + this.getPrice() + this.ComplianceFee;
	}
	
}