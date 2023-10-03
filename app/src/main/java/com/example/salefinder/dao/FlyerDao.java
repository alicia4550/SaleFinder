package com.example.salefinder.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.salefinder.entity.Flyer;

import java.util.List;

@Dao
public interface FlyerDao {
    @Query("SELECT * FROM flyer")
    List<Flyer> getAll();

    @Query("SELECT * FROM flyer WHERE id IN (:flyerIds)")
    List<Flyer> loadAllByIds(int[] flyerIds);

    @Query("SELECT * FROM flyer WHERE merchant LIKE :merchant")
    List<Flyer> findByMerchant(String merchant);

    @Insert
    void insert(Flyer flyer);

    @Insert
    void insertFlyers(Flyer... flyers);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllFlyers(List<Flyer> flyer);

    @Update
    void update(Flyer flyer);

    @Update
    void updateFlyers(Flyer... flyers);

    @Delete
    void delete(Flyer flyer);

    @Delete
    void deleteFlyers(Flyer... flyers);

}

