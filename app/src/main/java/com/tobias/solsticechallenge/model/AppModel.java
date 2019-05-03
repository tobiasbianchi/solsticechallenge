package com.tobias.solsticechallenge.model;

import java.util.ArrayList;
import java.util.List;
/**Instance of the model for the app with Singleton*/
public class AppModel {
    private static AppModel instance = null;
    public static AppModel getInstance() {
        if (instance == null) {
            instance = new AppModel();
        }
        return instance;
    }

    private List<Contact> contacts = new ArrayList<>();
    private Integer selectedContact = null;

    private AppModel() {
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void selectContact(int id) {
        this.selectedContact = id;
    }

    public void unselectContact(){
        selectedContact = null;
    }

    public Contact getSelectedContact() {
        if( selectedContact != null ) {
            for( Contact contact : contacts) {
                if( contact.getId() == selectedContact ) {
                    return contact;
                }
            }
        }
        return null;
    }

    public void toggleFavorite(int id) {
        Contact contact = getContactById(id);
        if(contact != null){
            contact.setFavorite(!contact.isFavorite());
        }
    }

    private Contact getContactById(int id) {
        for(Contact contact: contacts){
            if(contact.getId() == id){
                return contact;
            }
        }
        return null;
    }
}
