package com.example.salefinder.ui.model;

public class SalesItem {
    private String name;
    private float price;

    public SalesItem(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }
}
