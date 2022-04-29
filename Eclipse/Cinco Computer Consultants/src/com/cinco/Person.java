package com.cinco;

import java.util.Arrays;

public class Person {


    public Person(String code, String name, String address, String email) {
        String[] NameToken = name.split(",");
        Code = code;
        LastName = NameToken[0];
        FirstName = NameToken[1].trim();
        Address = new Address(address);
        if(email.length()>0){
            String[] EmailToken = email.split(",");
            EmailAddresses = EmailToken;
        } else {
            String[] Blank = {};
            EmailAddresses = Blank;
        }
    }

    private String Code;
    private String FirstName;
    private String LastName;
    private Address Address;
    private String[] EmailAddresses;

    public String getCode() {
        return Code;
    }

    public String getLastName() {
        return LastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getFullName() {
    	return FirstName + " " + LastName;
    }
    
    public Address getAddress() {
        return Address;
    }

    public String[] getEmailAddresses() {
        return EmailAddresses;
    }

    public String ToString(){
        return Code + ", " + LastName + ", " + FirstName + ", " + this.Address.ToString() + ", " + Arrays.toString(EmailAddresses);
    }
}