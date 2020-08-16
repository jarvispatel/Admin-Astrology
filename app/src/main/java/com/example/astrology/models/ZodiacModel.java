package com.example.astrology.models;

import java.io.Serializable;

public class ZodiacModel implements Serializable {
    int zodiacTxt, zodiacImg;

    public ZodiacModel(int zodiacImg, int zodiacTxt) {
        this.zodiacImg = zodiacImg;
        this.zodiacTxt = zodiacTxt;
    }

    public int getZodiacImg() {
        return zodiacImg;
    }

    public void setZodiacImg(int zodiacImg) {
        this.zodiacImg = zodiacImg;
    }

    public int getZodiacTxt() {
        return zodiacTxt;
    }

    public void setZodiacTxt(int zodiacTxt) {
        this.zodiacTxt = zodiacTxt;
    }
}
