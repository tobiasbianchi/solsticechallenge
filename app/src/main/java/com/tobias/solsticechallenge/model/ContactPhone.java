package com.tobias.solsticechallenge.model;

public class ContactPhone {
    public enum Type {
        HOME,
        WORK,
        MOBILE
    }
    private Type type;
    private String phone;

    public ContactPhone(Type type, String phone) {
        this.type = type;
        this.phone = phone;
    }

    public Type getType() {
        return type;
    }

    public String getPhone() {
        return phone;
    }
}
