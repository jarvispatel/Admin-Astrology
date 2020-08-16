package com.example.astrology.models;

public class HomeItemsModel {

    String home_item_label, home_item_back_image, item_key, item_type;

    public HomeItemsModel(String home_item_label, String home_item_back_image, String item_key, String item_type) {
        this.home_item_label = home_item_label;
        this.home_item_back_image = home_item_back_image;
        this.item_key = item_key;
        this.item_type = item_type;
    }

    public HomeItemsModel() {
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public String getHome_item_label() {
        return home_item_label;
    }

    public void setHome_item_label(String home_item_label) {
        this.home_item_label = home_item_label;
    }

    public String getHome_item_back_image() {
        return home_item_back_image;
    }

    public void setHome_item_back_image(String home_item_back_image) {
        this.home_item_back_image = home_item_back_image;
    }

    public String getItem_key() {
        return item_key;
    }

    public void setItem_key(String item_key) {
        this.item_key = item_key;
    }
}
