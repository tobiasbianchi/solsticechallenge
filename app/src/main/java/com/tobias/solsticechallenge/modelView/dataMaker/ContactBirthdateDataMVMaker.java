package com.tobias.solsticechallenge.modelView.dataMaker;

import android.content.Context;

import com.tobias.solsticechallenge.R;
import com.tobias.solsticechallenge.model.Contact;
import com.tobias.solsticechallenge.modelView.ContactInfoDataModelView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ContactBirthdateDataMVMaker extends ContactInfoDataItemMVMaker {
    @Override
    public ContactInfoDataModelView prepare(Context context, Contact contact) {
        return new ContactInfoDataModelView(context.getString(R.string.birthdate_title),
                new SimpleDateFormat("MMMM d, yyyy",
                        Locale.forLanguageTag(Locale.US.toLanguageTag()))
                        .format(contact.getBirthdate()));
    }
}
