package com.tobias.solsticechallenge.modelView;

public class ContactInfoDataModelView {
    private String title;
    private String data;
    private String comment;

    public ContactInfoDataModelView(String title, String data, String comment){
        this.title = title;
        this.data = data;
        this.comment = comment;
    }

    public ContactInfoDataModelView(String title, String data) {
        this(title,data,null);
    }

    public String getTitle() {
        return title;
    }

    public String getData() {
        return data;
    }

    public String getComment() {
        return comment;
    }

    public boolean hasComment() {
        return comment != null && !comment.isEmpty();
    }
}
