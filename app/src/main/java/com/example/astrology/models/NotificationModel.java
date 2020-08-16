package com.example.astrology.models;

public class NotificationModel {

    String notification_title, notification_description, item_key, notification_time, notification_long_description;

    public NotificationModel(String notification_title, String notification_description, String item_key, String notification_time, String notification_long_description) {
        this.notification_title = notification_title;
        this.notification_description = notification_description;
        this.item_key = item_key;
        this.notification_time = notification_time;
        this.notification_long_description = notification_long_description;
    }

    public String getNotification_title() {
        return notification_title;
    }

    public void setNotification_title(String notification_title) {
        this.notification_title = notification_title;
    }

    public String getNotification_description() {
        return notification_description;
    }

    public void setNotification_description(String notification_description) {
        this.notification_description = notification_description;
    }

    public String getItem_key() {
        return item_key;
    }

    public void setItem_key(String item_key) {
        this.item_key = item_key;
    }

    public String getNotification_time() {
        return notification_time;
    }

    public void setNotification_time(String notification_time) {
        this.notification_time = notification_time;
    }

    public NotificationModel() {
    }

    public String getNotification_long_description() {
        return notification_long_description;
    }

    public void setNotification_long_description(String notification_long_description) {
        this.notification_long_description = notification_long_description;
    }
}
