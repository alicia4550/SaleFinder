package com.example.salefinder.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.salefinder.dao.FlyerDao;
import com.example.salefinder.dao.ItemDao;
import com.example.salefinder.entity.Flyer;
import com.example.salefinder.entity.Item;

@Database(entities = {Flyer.class, Item.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FlyerDao flyerDao();
    public abstract ItemDao itemDao();
}
