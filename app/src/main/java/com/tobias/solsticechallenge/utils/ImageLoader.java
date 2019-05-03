package com.tobias.solsticechallenge.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageLoader {
    public static void loadImage(Context context, ImageView destination, Uri imageUri, int placeholderResource){
        Picasso.with(context)
                .load(imageUri)
                .placeholder(placeholderResource)
                .error(placeholderResource)
                .into(destination);
    }
}
