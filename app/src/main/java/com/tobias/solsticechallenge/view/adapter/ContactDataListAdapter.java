package com.tobias.solsticechallenge.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tobias.solsticechallenge.R;
import com.tobias.solsticechallenge.modelView.ContactInfoDataModelView;

import java.util.List;

/**Adapter for the Contact Info Items*/
public class ContactDataListAdapter
        extends RecyclerView.Adapter<ContactDataListAdapter.ViewHolder> {

    private List<ContactInfoDataModelView> data;

    public ContactDataListAdapter(List<ContactInfoDataModelView> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.contact_data_item_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ContactInfoDataModelView viewModel = data.get(i);
        viewHolder.setTitle(viewModel.getTitle());
        viewHolder.setData(viewModel.getData());
        viewHolder.setComment(viewModel.getComment());
        viewHolder.setCommentVisible(viewModel.hasComment());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView title, comment, data;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.data_title);
            comment = itemView.findViewById(R.id.data_comment);
            data = itemView.findViewById(R.id.data_info);
        }

        void setTitle(String title) {
            this.title.setText(
                    this.title.getContext()
                            .getString(R.string.title_format, title.toUpperCase()));
        }

        void setData(String data) {
            this.data.setText(data);
        }

        void setComment(String comment) {
            this.comment.setText(comment);
        }

        void setCommentVisible(boolean hasComment) {
            comment.setVisibility(hasComment ? View.VISIBLE : View.INVISIBLE);
        }
    }
}
