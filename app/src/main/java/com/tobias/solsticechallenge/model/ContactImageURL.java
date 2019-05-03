package com.tobias.solsticechallenge.model;

import android.net.Uri;

public class ContactImageURL {
    private Uri large, small;

    public ContactImageURL(Uri smallURL, Uri largeURL) {
        this.large = largeURL;
        this.small = smallURL;
    }

    public Uri getLarge() {
        return large;
    }

    public Uri getSmall() {
        return small;
    }
}
