package com.cinco;

public class Address {


    /*public Address(String street, String city, String state, String zip, String country) {
        Street = street;
        City = city;
        State = state;
        Zip = zip;
        Country = country;
    }*/

    private String Street;
    private String City;
    private String State;
    private String Zip;
    private String Country;

    public Address(String string) {
        String[] AddressToken = string.split(",", -1);
        Street = AddressToken[0];
        City = AddressToken[1];
        State = AddressToken[2];
        Zip = AddressToken[3];
        Country = AddressToken[4];
    }
    
    public String getStreet() {
        return Street;
    }

    public String getCity() {
        return City;
    }

    public String getState() {
        return State;
    }

    public String getZip() {
        return Zip;
    }

    public String getCountry() {
        return Country;
    }

    public String ToString() {
        return Street + " "+ City + ", " + State + " " + Zip + ", " + Country;
    }

}