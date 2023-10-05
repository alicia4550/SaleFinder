package com.example.salefinder.ui.model;

import java.io.Serializable;

public class ListItem implements Serializable {
    private String name;

    public ListItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
