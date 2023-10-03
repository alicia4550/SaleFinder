package com.example.salefinder.service;

import com.example.salefinder.dao.FlyerDao;
import com.example.salefinder.database.AppDatabase;
import com.example.salefinder.entity.Flyer;

import java.util.List;

public class FlyerService {
    private AppDatabase appDatabase;
    private FlyerDao flyerDao;

    public FlyerService(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
        this.flyerDao = this.appDatabase.flyerDao();
    }

    public void saveFlyersFromFlipp() {
        List<Flyer> flyers = WebScraperService.getAllFlyers();
        appDatabase.flyerDao().insertAllFlyers(flyers);
    }

    public List<Flyer> findByMerchant(String merchant) {
        return flyerDao.findByMerchant(merchant);
    }
}
