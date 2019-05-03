package com.tobias.solsticechallenge.view.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tobias.solsticechallenge.R;

public class SectionTitleViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    public SectionTitleViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setTag(this);
        title = itemView.findViewById(R.id.section_title);
    }

    public void setTitle(int textId) {
        title.setText(textId);
    }
}
