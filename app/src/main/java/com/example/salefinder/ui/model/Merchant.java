package com.example.salefinder.ui.model;

import java.util.List;

public class Merchant {
    private String name;
    private List<SalesItem> salesItemList;

    public Merchant(String name, List<SalesItem> salesItemList) {
        this.name = name;
        this.salesItemList = salesItemList;
    }

    public String getName() {
        return name;
    }

    public List<SalesItem> getSalesItemList() {
        return salesItemList;
    }
}
