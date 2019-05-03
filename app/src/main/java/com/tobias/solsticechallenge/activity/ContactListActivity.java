package com.tobias.solsticechallenge.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.tobias.solsticechallenge.R;
import com.tobias.solsticechallenge.model.AppModel;
import com.tobias.solsticechallenge.model.Contact;
import com.tobias.solsticechallenge.modelView.ContactItemModelView;
import com.tobias.solsticechallenge.services.LoadContactsTask;
import com.tobias.solsticechallenge.services.LoadedContactsInterface;
import com.tobias.solsticechallenge.view.adapter.ContactItemListAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.tobias.solsticechallenge.services.LoadedContactsInterface.ERROR.CONNECTION;

/**Activity for the display of the contact list*/
public class ContactListActivity extends AppCompatActivity
        implements LoadedContactsInterface,
        ContactItemListAdapter.OnContactClickedInterface{
    private RecyclerView recyclerView;
    private ContactItemListAdapter adapter;
    private Snackbar loadingSnackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.contact_list);
        recyclerView = findViewById(R.id.contact_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        loadContacts();
    }

    @Override
    protected void onResume(){
        super.onResume();
        Contact selectedContact = AppModel.getInstance().getSelectedContact();
        if(selectedContact != null){
            adapter.verifyFavoriteChange(selectedContact.getId(),selectedContact.isFavorite());
            AppModel.getInstance().unselectContact();
        }
    }

    @Override
    public void onLoadedContacts(List<Contact> contactList) {
        AppModel.getInstance().setContacts(contactList);
        adapter = new ContactItemListAdapter(transformModelToView(contactList), this);
        recyclerView.setAdapter(adapter);
        dismissSnackbar();
    }

    @Override
    public void onFailedLoading(ERROR error) {
        Toast.makeText(this.getApplicationContext(), getErrorMessage(error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onContactClicked(int id) {
        AppModel.getInstance().selectContact(id);
        startActivity(new Intent(this, ContactInfoActivity.class));
    }

    private void loadContacts(){
        loadingSnackbar = Snackbar.make(recyclerView,R.string.loading,Snackbar.LENGTH_INDEFINITE);
        loadingSnackbar.show();
        new LoadContactsTask(this).execute();
    }

    private void dismissSnackbar(){
        if(loadingSnackbar != null){
            loadingSnackbar.dismiss();
            loadingSnackbar = null;
        }
    }

    private int getErrorMessage(ERROR error) {
        dismissSnackbar();
        if( error == CONNECTION ){
            return R.string.connection_error;
        }
        return R.string.couldnt_load_list;
    }

    private List<ContactItemModelView> transformModelToView(List<Contact> contactList) {
        List<ContactItemModelView> modelViews = new ArrayList<>();
        for( Contact contact : contactList ){
            modelViews.add(new ContactItemModelView(contact));
        }
        return modelViews;
    }
}
