package com.tobias.solsticechallenge.modelView;

import java.util.Collections;
import java.util.List;

public class ContactListModelView {
    private final static int AMOUNT_TITLES = 2;
    private List<ContactItemModelView> contacts;

    public ContactListModelView(List<ContactItemModelView> contactsList) {
        this.contacts = contactsList;
        sort();
    }

    public int viewSize() {
        return contacts.size() + AMOUNT_TITLES;
    }

    public boolean hasContact(int listIndex)
    {
        return getContact(listIndex) != null;
    }

    public ContactItemModelView getContact(int listIndex) {
        ContactItemModelView found = getFavoriteContact(listIndex);
        if( found == null ){
            found = getNotFavoriteContact(listIndex);
        }
        return found;
    }

    private ContactItemModelView getFavoriteContact(int listIndex) {
        return getPossibleContact(listIndex, true);
    }

    private ContactItemModelView getNotFavoriteContact(int listIndex) {
        return getPossibleContact(listIndex, false);
    }

    private ContactItemModelView getPossibleContact(int listIndex, boolean favorite) {
        int moved = listIndex - getShiftedIndex(favorite);
        if( validIndex(moved)
                && contacts.get(moved).isFavorite() == favorite) {
            return contacts.get(moved);
        }
        return null;
    }

    private boolean validIndex(int index) {
        return index >= 0 && index < contacts.size();
    }

    public boolean isFavoriteTitle(int i) {
        return i == 0;
    }

    public void sort() {
        Collections.sort(contacts, (o1, o2) -> {
            if( o1.isFavorite() == o2.isFavorite() ) {
                return o1.getName().compareTo(o2.getName());
            }
            if( o1.isFavorite() ){
                return -1;
            }
            return 1;
        });
    }

    public int getIndexInListOfContact(int id) {
        for(int i = 0; i < contacts.size(); i++){
            if(contacts.get(i).getId() == id)
            {
                return i + getShiftedIndex(contacts.get(i).isFavorite());
            }
        }
        return -1;
    }

    private int getShiftedIndex(boolean isFavorite){
        return isFavorite ? 1 : AMOUNT_TITLES;
    }
}
