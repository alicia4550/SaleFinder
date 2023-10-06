package com.example.salefinder.ui.model;

import com.example.salefinder.entity.Item;

import java.util.Objects;

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

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Item)) {
            return false;
        }

        SalesItem that = (SalesItem) other;

        return Objects.equals(this.name, that.name) && this.price == that.price;
    }
}
