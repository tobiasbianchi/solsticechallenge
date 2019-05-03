package com.tobias.solsticechallenge.modelView.dataMaker;

import android.content.Context;

import com.tobias.solsticechallenge.R;
import com.tobias.solsticechallenge.model.Contact;
import com.tobias.solsticechallenge.modelView.ContactInfoDataModelView;

import java.util.ArrayList;
import java.util.List;

/**Abstraction that creates a contact info data model view*/
public abstract class ContactInfoDataItemMVMaker {
    /** @return The model view or null if the data couldn't be prepared*/
    public abstract ContactInfoDataModelView prepare(Context context, Contact contact);
}
