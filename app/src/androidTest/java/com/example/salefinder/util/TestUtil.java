package com.example.salefinder.util;

import static java.lang.Float.parseFloat;

import com.example.salefinder.entity.Flyer;
import com.example.salefinder.entity.Item;
import com.example.salefinder.ui.model.ListItem;
import com.example.salefinder.ui.model.Merchant;
import com.example.salefinder.ui.model.SalesItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestUtil {
    public static Flyer createFlyer(int id, String merchant, String logoUrl) {
        Flyer flyer = new Flyer();
        flyer.id = id;
        flyer.merchant = merchant;
        flyer.storefront_logo_url = logoUrl;
        return flyer;
    }

    public static Item createItem(int flyerId, String name) {
        Item item = new Item();
        item.flyer_id = flyerId;
        item.name = name;
        return item;
    }

    public static Item createItem(int flyerId, String name, String price) {
        Item item = new Item();
        item.flyer_id = flyerId;
        item.name = name;
        item.price = price;
        return item;
    }

    public static List<Merchant> createMerchantList() {
        Merchant merchant1 = new Merchant("merchant1", "url1", Arrays.asList(12,34));
        Merchant merchant2 = new Merchant("merchant2", "url2", Arrays.asList(56));
        List<Merchant> merchantList = new ArrayList<>();
        merchantList.add(merchant1);
        merchantList.add(merchant2);
        return merchantList;
    }

    public static List<ListItem> createListItems() {
        ListItem listItem1 = new ListItem("item1");
        ListItem listItem2 = new ListItem("item2");
        List<ListItem> listItems = new ArrayList<>();
        listItems.add(listItem1);
        listItems.add(listItem2);
        return listItems;
    }

    public static SalesItem toSalesItem(Item item) {
        return new SalesItem(item.name, parseFloat(item.price));
    }
}

