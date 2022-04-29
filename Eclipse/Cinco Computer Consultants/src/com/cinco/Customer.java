package com.cinco;

public class Customer {

	private String Code;
	private String Name;
	private Address Address;
	private Person PrimaryContact;
	private String Type;

	public Customer(String code,
					String type,
					Person primaryContact,
					String name,
					String address) {
		Code = code;
		Name = name;
		Address = new Address(address);
		PrimaryContact = primaryContact;
		Type = type;
	}

	public String getCode() {
		return Code;
	}

	public String getType() {
		return Type;
	}

	public Person getPrimaryContact() {
		return PrimaryContact;
	}

	public String getName() {
		return Name;
	}

	public Address getAddress() {
		return Address;
	}

	public String printInfo() {
		String returnString = this.Code + this.Type + this.PrimaryContact + this.Name;
		return returnString;
	}
}