package com.example.salefinder.util;

import com.example.salefinder.entity.Flyer;
import com.example.salefinder.entity.Item;

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
}

