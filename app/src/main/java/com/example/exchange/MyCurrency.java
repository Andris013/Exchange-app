package com.example.exchange;

//Modell osztály
public class MyCurrency {
    private String name;
    private float rate;

    public MyCurrency(String name, float rate) {
        this.name = name;
        this.rate = rate;
    }

    public MyCurrency() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

}
