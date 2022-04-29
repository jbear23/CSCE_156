package com.cinco;

//Cost is a class that is made to encapsulate a invoice 
//items fees, taxes, and price neatly, as to avoid the use of 2d arrays.
//Cost is never to be used for more permanent storage of a invoice item, as they can be easily created again,
//merely it removes the need to use arrays with "Magic Numbers" assigned to
//Fees, taxes, and price. 
public class Cost {
	
	private String ItemID;
	private Double Fee;
	private Double Tax;
	private Double Price;
	
	public String getItemID() {
		return ItemID;
	}
	public Double getFee() {
		return Fee;
	}
	public Double getTaxes() {
		return Tax;
	}
	public Double getPrice() {
		return Price;
	}
	public Double getTotal() {
		return Fee + Tax + Price;
	}
	
	public Cost(Double Price, Double Tax, Double Fee, String ID) {
		this.Fee = Fee;
		this.Tax = Tax;
		this.Price = Price;
		this.ItemID = ID;
	}

}