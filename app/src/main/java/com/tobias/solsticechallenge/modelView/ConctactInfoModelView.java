package com.tobias.solsticechallenge.modelView;

import android.content.Context;
import android.net.Uri;

import com.tobias.solsticechallenge.model.Contact;

import java.util.List;

public class ConctactInfoModelView {
    private int id;
    private Uri image;
    private String name, companyName;

    private boolean isFavorite;
    private List<ContactInfoDataModelView> data;

    public ConctactInfoModelView(Context context, Contact contact)
    {
        this.id = contact.getId();
        this.image = contact.getImageURL().getLarge();
        this.name = contact.getName();
        this.companyName = contact.getCompanyName();
        this.isFavorite = contact.isFavorite();
        this.data = new ContactInfoDataMaker().prepare(context, contact);
    }

    public int getId() {
        return id;
    }

    public Uri getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setFavorite(boolean favorite)
    {
        this.isFavorite = favorite;
    }
    public boolean isFavorite() {
        return isFavorite;
    }

    public List<ContactInfoDataModelView> getData() {
        return data;
    }

    public boolean hasCompanyName() {
        return companyName != null && !companyName.isEmpty();
    }
}
