package com.tobias.solsticechallenge.modelView.dataMaker;

import android.content.Context;

import com.tobias.solsticechallenge.R;
import com.tobias.solsticechallenge.model.Contact;
import com.tobias.solsticechallenge.model.ContactAddress;
import com.tobias.solsticechallenge.modelView.ContactInfoDataModelView;

public class ContactAddressDataMVMaker extends ContactInfoDataItemMVMaker {
    @Override
    public ContactInfoDataModelView prepare(Context context, Contact contact) {
        ContactAddress address = contact.getAddress();
        return new ContactInfoDataModelView(context.getString(R.string.address_title),
                context.getString(R.string.address_info_format,
                        contact.getAddress().getStreet(),
                        context.getString(R.string.address_info_format_second_line,
                                address.getCity(), address.getState(),
                                address.getZipCode(), address.getCountry())));
    }
}
