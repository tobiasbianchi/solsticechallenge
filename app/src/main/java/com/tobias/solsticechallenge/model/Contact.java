package com.tobias.solsticechallenge.model;

import java.util.Date;
import java.util.List;

public class Contact {
    private int id;
    private String name, companyName, email;
    private Date birthdate;
    private ContactAddress address;
    private List<ContactPhone> phones;
    private ContactImageURL imageURL;
    private boolean isFavorite;

    public Contact(int id, String name, String companyName, String email,
                   Date birthdate, ContactAddress address, List<ContactPhone> phones,
                   ContactImageURL imageURL, boolean isFavorite){
        this.id = id;
        this.name = name;
        this.companyName = companyName;
        this.email = email;
        this.birthdate = birthdate;
        this.address = address;
        this.phones = phones;
        this.imageURL = imageURL;
        this.isFavorite = isFavorite;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public ContactAddress getAddress() {
        return address;
    }

    public List<ContactPhone> getPhones() {
        return phones;
    }

    public ContactImageURL getImageURL() {
        return imageURL;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
