package com.example.salefinder.module;

import android.content.Context;

import androidx.room.Room;

import com.example.salefinder.dao.FlyerDao;
import com.example.salefinder.dao.ItemDao;
import com.example.salefinder.database.AppDatabase;
import com.example.salefinder.repository.FlyerRepository;
import com.example.salefinder.repository.ItemRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
    private AppDatabase appDatabase;

    public DatabaseModule(Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "grocery-db.db").build();
    }

    @Singleton
    @Provides
    AppDatabase providesAppDatabase() {
        return appDatabase;
    }

    @Provides
    @Singleton
    FlyerRepository providesFlyerRepository(FlyerDao flyerDao){
        return new FlyerRepository(flyerDao);
    }

    @Singleton
    @Provides
    FlyerDao providesFlyerDao(AppDatabase appDatabase) {
        return appDatabase.flyerDao();
    }

    @Provides
    @Singleton
    ItemRepository providesItemRepository(ItemDao itemDao){
        return new ItemRepository(itemDao);
    }

    @Singleton
    @Provides
    ItemDao providesItemDao(AppDatabase appDatabase) {
        return appDatabase.itemDao();
    }
}
