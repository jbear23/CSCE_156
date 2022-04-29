package com.cinco;

public class Consultation extends Product {

	private Person Consultant;
	private String PricePerHour;
	
	public Consultation(String Code,
						String Name,
						String Type,
						Person ConsultantPerson,
						String HourlyFee) {
		super(Code, Name, Type);
		Consultant = ConsultantPerson;
		PricePerHour = HourlyFee;
	}

	public Person getConsultant() {
		return Consultant;
	}

	public String getHourlyFee() {
		return PricePerHour;
	}

}