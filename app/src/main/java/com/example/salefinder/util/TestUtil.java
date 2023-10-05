package com.example.salefinder.util;

import com.example.salefinder.entity.Flyer;
import com.example.salefinder.entity.Item;
import com.example.salefinder.ui.model.Merchant;
import com.example.salefinder.ui.model.SalesItem;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {
    public static Flyer createFlyer(int id, String merchant) {
        Flyer flyer = new Flyer();
        flyer.id = id;
        flyer.merchant = merchant;
        return flyer;
    }

    public static Item createItem(int flyerId, String name) {
        Item item = new Item();
        item.flyer_id = flyerId;
        item.name = name;
        return item;
    }

//    public List<Merchant> getTestMerchantList() {
//        SalesItem salesItem1 = new SalesItem("name1", 1);
//        SalesItem salesItem2 = new SalesItem("name2", 1);
//        List<SalesItem> salesItemList1 = new ArrayList<>();
//        salesItemList1.add(salesItem1);
//        salesItemList1.add(salesItem2);
//        Merchant merchant1 = new Merchant("merchant1", salesItemList1);
//        SalesItem salesItem3 = new SalesItem("name3", 3);
//        SalesItem salesItem4 = new SalesItem("name4", 4);
//        List<SalesItem> salesItemList2 = new ArrayList<>();
//        salesItemList2.add(salesItem3);
//        salesItemList2.add(salesItem4);
//        Merchant merchant2 = new Merchant("merchant2", salesItemList2);
//        List<Merchant> merchantList = new ArrayList<>();
//        merchantList.add(merchant1);
//        merchantList.add(merchant2);
//
//        return merchantList;
//    }
}

