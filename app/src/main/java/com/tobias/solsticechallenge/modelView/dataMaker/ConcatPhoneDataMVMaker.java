package com.tobias.solsticechallenge.modelView.dataMaker;

import android.content.Context;

import com.tobias.solsticechallenge.R;
import com.tobias.solsticechallenge.model.Contact;
import com.tobias.solsticechallenge.model.ContactPhone;
import com.tobias.solsticechallenge.modelView.ContactInfoDataModelView;

import java.util.List;

public class ConcatPhoneDataMVMaker extends ContactInfoDataItemMVMaker
{
    private ContactPhone.Type type;

    public ConcatPhoneDataMVMaker(ContactPhone.Type type)
    {
        this.type = type;
    }

    @Override
    public ContactInfoDataModelView prepare(Context context, Contact contact) {
        List<ContactPhone> contactPhone = contact.getPhones();
        for(ContactPhone phone : contactPhone)
        {
            if( phone.getType() == type )
            {
                return new ContactInfoDataModelView(
                        context.getString(R.string.phone_title),
                        phone.getPhone(),context.getString(getNameId(type)));
            }
        }
        return null;
    }

    private int getNameId(ContactPhone.Type type)
    {
        switch (type)
        {
            case HOME:
                return R.string.home;
            case WORK:
                return R.string.work;
            case MOBILE:
                return R.string.mobile;
        }
        return R.string.other;
    }
}
