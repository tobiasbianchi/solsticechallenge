package com.tobias.solsticechallenge.services;

import com.tobias.solsticechallenge.model.Contact;

import java.util.List;

/**Inteface that reacts to loaded contacts events*/
public interface LoadedContactsInterface {
    enum ERROR{ CONNECTION, JSON_FORMAT}
    void onLoadedContacts(List<Contact> contactList);
    void onFailedLoading(ERROR error);
}
