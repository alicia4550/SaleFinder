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

    @Query("SELECT * FROM flyer WHERE merchant LIKE :merchant AND categories_csv LIKE '%' || 'Groceries' || '%'")
    List<Flyer> findByMerchant(String merchant);

    @Query("SELECT id FROM flyer WHERE merchant LIKE :merchant AND categories_csv LIKE '%' || 'Groceries' || '%'")
    List<Integer> findFlyerIdByMerchant(String merchant);

    @Query("SELECT DISTINCT storefront_logo_url FROM flyer WHERE merchant LIKE :merchant  AND categories_csv LIKE '%' || 'Groceries' || '%'")
    List<String> findLogoByMerchant(String merchant);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Flyer flyer);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllFlyers(List<Flyer> flyers);

    @Update
    void update(Flyer flyer);

    @Delete
    void delete(Flyer flyer);

}

