package com.example.astrology.models;

public class ComplaintModel {

    String complaint_title, complaint_description, complaint_date;

    public ComplaintModel(String complaint_title, String complaint_description, String complaint_date) {
        this.complaint_title = complaint_title;
        this.complaint_description = complaint_description;
        this.complaint_date = complaint_date;
    }

    public ComplaintModel() {
    }

    public String getComplaint_title() {
        return complaint_title;
    }

    public void setComplaint_title(String complaint_title) {
        this.complaint_title = complaint_title;
    }

    public String getComplaint_description() {
        return complaint_description;
    }

    public void setComplaint_description(String complaint_description) {
        this.complaint_description = complaint_description;
    }

    public String getComplaint_date() {
        return complaint_date;
    }

    public void setComplaint_date(String complaint_date) {
        this.complaint_date = complaint_date;
    }
}
