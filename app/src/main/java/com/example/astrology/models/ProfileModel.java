package com.example.astrology.models;

public class ProfileModel {

    String user_name, phone_number;

    public ProfileModel() {
    }

    public ProfileModel(String user_name, String phone_number) {
        this.user_name = user_name;
        this.phone_number = phone_number;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

}
