package com.example.salefinder.ui.model;

import com.example.salefinder.entity.Item;

import java.util.Objects;

public class SalesItem {
    private String name;
    private float price;

    private String cutoutUrl;

    public SalesItem(String name, float price, String cutoutUrl) {
        this.name = name;
        this.price = price;
        this.cutoutUrl = cutoutUrl;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getCutoutUrl() {
        return cutoutUrl;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Item)) {
            return false;
        }

        SalesItem that = (SalesItem) other;

        return Objects.equals(this.name, that.name) && this.price == that.price && Objects.equals(this.cutoutUrl, that.cutoutUrl);
    }
}
