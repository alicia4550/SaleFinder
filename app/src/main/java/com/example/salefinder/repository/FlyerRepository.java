package com.example.salefinder.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.salefinder.dao.FlyerDao;
import com.example.salefinder.entity.Flyer;

import java.util.List;

import javax.inject.Inject;

public class FlyerRepository {
    private final FlyerDao flyerDao;

    @Inject
    public FlyerRepository(FlyerDao flyerDao) {
        this.flyerDao = flyerDao;
    }

    public List<Flyer> getAll() {
        return flyerDao.getAll();
    }

    public List<Flyer> loadAllByIds(int[] flyerIds) {
        return flyerDao.loadAllByIds(flyerIds);
    }

    public List<Flyer> findByMerchant(String merchant) {
        return flyerDao.findByMerchant(merchant);
    }

    public List<Integer> findFlyerIdByMerchant(String merchant) {
        return flyerDao.findFlyerIdByMerchant(merchant);
    }

    public void insert(Flyer flyer) {
        flyerDao.insert(flyer);
    }

    public void insertAllFlyers(List<Flyer> flyers) {
        flyerDao.insertAllFlyers(flyers);
    }

    public void update(Flyer flyer) {
        flyerDao.update(flyer);
    }

    public void delete(Flyer flyer) {
        flyerDao.delete(flyer);
    }

}
