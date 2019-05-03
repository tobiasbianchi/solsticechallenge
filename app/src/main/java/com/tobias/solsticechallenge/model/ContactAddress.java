package com.tobias.solsticechallenge.model;

public class ContactAddress {
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;

    public ContactAddress(String street, String city, String state, String country, String zipCode){
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }


    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getZipCode() {
        return zipCode;
    }
}
