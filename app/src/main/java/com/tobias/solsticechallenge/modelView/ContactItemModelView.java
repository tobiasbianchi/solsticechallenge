package com.tobias.solsticechallenge.modelView;

import android.net.Uri;

import com.tobias.solsticechallenge.model.Contact;

public class ContactItemModelView {
    private int id;
    private String name, companyName;
    private boolean isFavorite;
    private Uri image;

    public ContactItemModelView(Contact contact) {
        this.id = contact.getId();
        this.name = contact.getName();
        this.companyName = contact.getCompanyName();
        this.isFavorite = contact.isFavorite();
        this.image = contact.getImageURL().getSmall();
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

    public boolean isFavorite() {
        return isFavorite;
    }

    public Uri getImage() {
        return image;
    }

    public boolean hasCompanyName() {
        return companyName != null && !companyName.isEmpty();
    }

    public void toggleFavorite() {
        this.isFavorite = !this.isFavorite;
    }
}
