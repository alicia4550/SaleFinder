package com.example.salefinder.service;

import com.example.salefinder.dao.ItemDao;
import com.example.salefinder.database.AppDatabase;
import com.example.salefinder.entity.Flyer;
import com.example.salefinder.entity.Item;

import java.util.Arrays;
import java.util.List;

public class ItemService {
    private AppDatabase appDatabase;
    private ItemDao itemDao;

    public ItemService(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
        this.itemDao = this.appDatabase.itemDao();
    }

    public void saveItemsByFlyerFromFlipp(int flyerId) {
        List<Item> items = WebScraperService.getAllItemsByFlyer(flyerId);
        appDatabase.itemDao().insertAllItems(items);
    }

    public List<Item> findByFlyerId(int flyerId) {
        return itemDao.findByFlyerId(flyerId);
    }
}
