package com.tobias.solsticechallenge.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tobias.solsticechallenge.R;
import com.tobias.solsticechallenge.modelView.ContactItemModelView;
import com.tobias.solsticechallenge.modelView.ContactListModelView;
import com.tobias.solsticechallenge.view.viewHolder.ContactViewHolder;
import com.tobias.solsticechallenge.view.viewHolder.SectionTitleViewHolder;

import java.util.List;

/**Adapter for the contact in a list*/
public class ContactItemListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    /**Listener interface to react when a contact is clicked*/
    public interface OnContactClickedInterface {
        void onContactClicked(int id);
    }

    private ContactListModelView contacts;
    private OnContactClickedInterface itemClickedHandler;

    private final static int CONTACT_TYPE = 0, SECTION_TITLE_TYPE = 1;

    public ContactItemListAdapter(
            List<ContactItemModelView> contacts, OnContactClickedInterface itemClickedHandler){
        this.contacts = new ContactListModelView(contacts);
        this.itemClickedHandler = itemClickedHandler;
    }

    @Override
    public int getItemViewType(int position) {
        if( contacts.hasContact(position) ) {
            return CONTACT_TYPE;
        }
        return SECTION_TITLE_TYPE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == SECTION_TITLE_TYPE) {
            return new SectionTitleViewHolder(LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.section_title, viewGroup, false));
        }
        return new ContactViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.contact_item_list, viewGroup, false), this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ContactItemModelView viewModel = contacts.getContact(i);
        if(viewModel != null) {
            drawContactViewHolder((ContactViewHolder) viewHolder, viewModel);
        } else {
            drawSectionTitle((SectionTitleViewHolder)viewHolder, i);
        }
    }

    @Override
    public int getItemCount() {
        return contacts.viewSize();
    }

    @Override
    public void onClick(View v) {
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder)v.getTag();
        ContactItemModelView viewModel = contacts.getContact(viewHolder.getAdapterPosition());
        if(viewModel != null) {
            itemClickedHandler.onContactClicked(viewModel.getId());
        }
    }

    public void verifyFavoriteChange(int id, boolean favorite) {
        int oldIndexInList = contacts.getIndexInListOfContact(id);
        ContactItemModelView viewModel = contacts.getContact(oldIndexInList);
        if(viewModel != null && viewModel.isFavorite() != favorite){
            viewModel.toggleFavorite();
            contacts.sort();
            int newIndexInList = contacts.getIndexInListOfContact(id);
            notifyItemMoved(oldIndexInList, newIndexInList);
            notifyItemChanged(newIndexInList);
        }
    }


    private void drawSectionTitle(SectionTitleViewHolder viewHolder, int i) {
        int textId = contacts.isFavoriteTitle(i) ? R.string.favorite_contacts : R.string.other_contacts;
        viewHolder.setTitle(textId);
    }

    private void drawContactViewHolder(ContactViewHolder viewHolder, ContactItemModelView viewModel) {
        viewHolder.loadImage(viewModel.getImage());
        viewHolder.setStar(viewModel.isFavorite());
        viewHolder.setName(viewModel.getName());
        viewHolder.setCompanyName(viewModel.getCompanyName());
        viewHolder.showCompanyName(viewModel.hasCompanyName());
    }


}
