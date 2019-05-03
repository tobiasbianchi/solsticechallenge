package com.tobias.solsticechallenge.modelView.dataMaker;

import android.content.Context;

import com.tobias.solsticechallenge.model.Contact;
import com.tobias.solsticechallenge.modelView.ContactInfoDataModelView;

import java.util.ArrayList;
import java.util.List;

/**Class that creates a list of info data model views*/
public class ContactMultipleDataMVMaker
{
    private List<ContactInfoDataItemMVMaker> makers = new ArrayList<>();

    public ContactMultipleDataMVMaker add(ContactInfoDataItemMVMaker maker) {
        if( !makers.contains(maker) ) {
            makers.add(maker);
        }
        return this;
    }

    public List<ContactInfoDataModelView> prepare(Context context, Contact contact) {
        List<ContactInfoDataModelView> list = new ArrayList<>();
        for(ContactInfoDataItemMVMaker maker : makers) {
            ContactInfoDataModelView view = maker.prepare(context, contact);
            if(view != null) list.add(view);
        }
        return list;
    }
}
