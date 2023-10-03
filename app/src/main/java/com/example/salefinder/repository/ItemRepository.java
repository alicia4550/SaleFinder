package com.example.salefinder.repository;

import com.example.salefinder.dao.ItemDao;
import com.example.salefinder.entity.Item;

import java.util.List;

import javax.inject.Inject;

public class ItemRepository {
    private final ItemDao itemDao;

    @Inject
    public ItemRepository(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public List<Item> getAll() {
        return itemDao.getAll();
    }

    public List<Item> loadAllByIds(int[] itemIds) {
        return itemDao.loadAllByIds(itemIds);
    }

    public List<Item> findByFlyerId(int flyerId) {
        return itemDao.findByFlyerId(flyerId);
    }

    public void insert(Item item) {
        itemDao.insert(item);
    }

    public void insertAllItems(List<Item> items) {
        itemDao.insertAllItems(items);
    }

    public void update(Item item) {
        itemDao.update(item);
    }

    public void delete(Item item) {
        itemDao.delete(item);
    }

}
