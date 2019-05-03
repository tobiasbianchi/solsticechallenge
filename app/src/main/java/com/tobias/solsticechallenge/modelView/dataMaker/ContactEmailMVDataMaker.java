package com.tobias.solsticechallenge.modelView.dataMaker;

import android.content.Context;

import com.tobias.solsticechallenge.R;
import com.tobias.solsticechallenge.model.Contact;
import com.tobias.solsticechallenge.modelView.ContactInfoDataModelView;

public class ContactEmailMVDataMaker extends ContactInfoDataItemMVMaker {
    @Override
    public ContactInfoDataModelView prepare(Context context, Contact contact) {
        return new ContactInfoDataModelView(context.getString(R.string.email_title),
                contact.getEmail());
    }
}
