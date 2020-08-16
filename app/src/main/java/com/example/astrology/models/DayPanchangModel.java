package com.example.astrology.models;

public class DayPanchangModel {

    String paksha, rashtriya, amant_label, purnimant_label, vikram_savant_amant, vikram_savant_purnimant, pramathi_amant, pramathi_purnimant, month_amant, month_purnimant;

    public DayPanchangModel(String paksha, String rashtriya, String amant_label, String purnimant_label, String vikram_savant_amant, String vikram_savant_purnimant, String pramathi_amant, String pramathi_purnimant, String month_amant, String month_purnimant) {
        this.paksha = paksha;
        this.rashtriya = rashtriya;
        this.amant_label = amant_label;
        this.purnimant_label = purnimant_label;
        this.vikram_savant_amant = vikram_savant_amant;
        this.vikram_savant_purnimant = vikram_savant_purnimant;
        this.pramathi_amant = pramathi_amant;
        this.pramathi_purnimant = pramathi_purnimant;
        this.month_amant = month_amant;
        this.month_purnimant = month_purnimant;
    }

    public DayPanchangModel() {
    }

    public String getPaksha() {
        return paksha;
    }

    public void setPaksha(String paksha) {
        this.paksha = paksha;
    }

    public String getRashtriya() {
        return rashtriya;
    }

    public void setRashtriya(String rashtriya) {
        this.rashtriya = rashtriya;
    }

    public String getAmant_label() {
        return amant_label;
    }

    public void setAmant_label(String amant_label) {
        this.amant_label = amant_label;
    }

    public String getPurnimant_label() {
        return purnimant_label;
    }

    public void setPurnimant_label(String purnimant_label) {
        this.purnimant_label = purnimant_label;
    }

    public String getVikram_savant_amant() {
        return vikram_savant_amant;
    }

    public void setVikram_savant_amant(String vikram_savant_amant) {
        this.vikram_savant_amant = vikram_savant_amant;
    }

    public String getVikram_savant_purnimant() {
        return vikram_savant_purnimant;
    }

    public void setVikram_savant_purnimant(String vikram_savant_purnimant) {
        this.vikram_savant_purnimant = vikram_savant_purnimant;
    }

    public String getPramathi_amant() {
        return pramathi_amant;
    }

    public void setPramathi_amant(String pramathi_amant) {
        this.pramathi_amant = pramathi_amant;
    }

    public String getPramathi_purnimant() {
        return pramathi_purnimant;
    }

    public void setPramathi_purnimant(String pramathi_purnimant) {
        this.pramathi_purnimant = pramathi_purnimant;
    }

    public String getMonth_amant() {
        return month_amant;
    }

    public void setMonth_amant(String month_amant) {
        this.month_amant = month_amant;
    }

    public String getMonth_purnimant() {
        return month_purnimant;
    }

    public void setMonth_purnimant(String month_purnimant) {
        this.month_purnimant = month_purnimant;
    }
}
