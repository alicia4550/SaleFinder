package com.example.salefinder.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.salefinder.entity.Flyer;
import com.example.salefinder.entity.Item;

import java.util.List;

@Dao
public interface ItemDao {
    @Query("SELECT * FROM item")
    List<Item> getAll();

    @Query("SELECT * FROM item WHERE id IN (:itemIds)")
    List<Item> loadAllByIds(int[] itemIds);

    @Query("SELECT * FROM item WHERE flyer_id = :flyerId")
    List<Item> findByFlyerId(int flyerId);

    @Insert
    void insert(Item item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllItems(List<Item> items);

    @Update
    void update(Item item);

    @Delete
    void delete(Item item);

}

