package com.tobias.solsticechallenge.modelView;

import com.tobias.solsticechallenge.model.ContactPhone;
import com.tobias.solsticechallenge.modelView.dataMaker.ConcatPhoneDataMVMaker;
import com.tobias.solsticechallenge.modelView.dataMaker.ContactAddressDataMVMaker;
import com.tobias.solsticechallenge.modelView.dataMaker.ContactBirthdateDataMVMaker;
import com.tobias.solsticechallenge.modelView.dataMaker.ContactEmailMVDataMaker;
import com.tobias.solsticechallenge.modelView.dataMaker.ContactMultipleDataMVMaker;

class ContactInfoDataMaker extends ContactMultipleDataMVMaker
{
    ContactInfoDataMaker()
    {
        add(new ConcatPhoneDataMVMaker(ContactPhone.Type.HOME))
                .add(new ConcatPhoneDataMVMaker(ContactPhone.Type.MOBILE))
                .add(new ConcatPhoneDataMVMaker(ContactPhone.Type.WORK))
                .add(new ContactAddressDataMVMaker())
                .add(new ContactBirthdateDataMVMaker())
                .add(new ContactEmailMVDataMaker());
    }
}
