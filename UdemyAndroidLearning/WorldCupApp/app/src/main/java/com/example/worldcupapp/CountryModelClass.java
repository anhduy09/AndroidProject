package com.example.worldcupapp;

public class CountryModelClass {
    private String countryName, cupWin;
    private int img;

    public CountryModelClass(String countryName, String cupWin, int img) {
        this.countryName = countryName;
        this.cupWin = cupWin;
        this.img = img;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCupWin() {
        return cupWin;
    }

    public void setCupWin(String cupWin) {
        this.cupWin = cupWin;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
