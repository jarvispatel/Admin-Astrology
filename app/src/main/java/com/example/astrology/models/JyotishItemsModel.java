package com.example.astrology.models;

public class JyotishItemsModel {

    String jyotish_title, jyotish_title_image, jyotish_description, item_key;

    public JyotishItemsModel(String jyotish_title, String jyotish_title_image, String jyotish_description, String item_key) {
        this.jyotish_title = jyotish_title;
        this.jyotish_title_image = jyotish_title_image;
        this.jyotish_description = jyotish_description;
        this.item_key = item_key;
    }

    public JyotishItemsModel() {
    }

    public String getItem_key() {
        return item_key;
    }

    public void setItem_key(String item_key) {
        this.item_key = item_key;
    }

    public String getJyotish_title() {
        return jyotish_title;
    }

    public void setJyotish_title(String jyotish_title) {
        this.jyotish_title = jyotish_title;
    }

    public String getJyotish_title_image() {
        return jyotish_title_image;
    }

    public void setJyotish_title_image(String jyotish_title_image) {
        this.jyotish_title_image = jyotish_title_image;
    }

    public String getJyotish_description() {
        return jyotish_description;
    }

    public void setJyotish_description(String jyotish_description) {
        this.jyotish_description = jyotish_description;
    }
}
