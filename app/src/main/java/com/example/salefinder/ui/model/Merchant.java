package com.example.salefinder.ui.model;

import java.util.ArrayList;
import java.util.List;

public class Merchant {
    private String name;

    private String logoUrl;

    private List<Integer> flyerIdList;

    private List<SalesItem> salesItemList;

    public Merchant(String name, String logoUrl, List<Integer> flyerIdList) {
        this.name = name;
        this.logoUrl = logoUrl;
        this.flyerIdList = flyerIdList;
        this.salesItemList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public List<Integer> getFlyerIdList() {
        return flyerIdList;
    }

    public List<SalesItem> getSalesItemList() {
        return salesItemList;
    }

    public void addSalesItems(List<SalesItem> newSalesItems) {
        salesItemList.addAll(newSalesItems);
    }
}
