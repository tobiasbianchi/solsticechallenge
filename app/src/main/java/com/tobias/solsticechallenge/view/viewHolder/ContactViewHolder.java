package com.tobias.solsticechallenge.view.viewHolder;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tobias.solsticechallenge.R;
import com.tobias.solsticechallenge.utils.ImageLoader;

public class ContactViewHolder extends RecyclerView.ViewHolder
{
    private ImageView contactImage;
    private TextView name, companyName, favoriteStar;

    public ContactViewHolder(
            @NonNull View itemView,
            View.OnClickListener onClickListener) {
        super(itemView);
        itemView.setTag(this);
        itemView.setOnClickListener(onClickListener);
        contactImage = itemView.findViewById(R.id.contact_image);
        favoriteStar = itemView.findViewById(R.id.contact_favorite_star);
        name = itemView.findViewById(R.id.contact_name);
        companyName = itemView.findViewById(R.id.contact_description);

    }

    public void loadImage(Uri path){
        ImageLoader.loadImage(contactImage.getContext(), contactImage, path, R.drawable.user_small);
    }

    public void setStar(boolean favorite) {
        setVisible(favoriteStar, favorite);
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setCompanyName(String companyName) {
        this.companyName.setText(companyName);
    }

    public void showCompanyName(boolean hasCompanyName) {
        setVisible(companyName, hasCompanyName);
    }

    private void setVisible(View view, boolean visible)
    {
        view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }
}
