package com.example.astrology.models;

public class HomeRashiSubDataModel {

    String title, title_image, description;

    public HomeRashiSubDataModel(String title, String title_image, String description) {
        this.title = title;
        this.title_image = title_image;
        this.description = description;
    }

    public HomeRashiSubDataModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_image() {
        return title_image;
    }

    public void setTitle_image(String title_image) {
        this.title_image = title_image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
