package com.example.salefinder.ui.model;

import java.util.ArrayList;
import java.util.List;

public class Merchant {
    private String name;

    private List<Integer> flyerIdList;

    private List<SalesItem> salesItemList;

    public Merchant(String name, List<Integer> flyerIdList) {
        this.name = name;
        this.flyerIdList = flyerIdList;
        this.salesItemList = new ArrayList<>();
    }

//    public Merchant(String name, List<SalesItem> salesItemList) {
//        this.name = name;
//        this.salesItemList = salesItemList;
//    }

    public String getName() {
        return name;
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
