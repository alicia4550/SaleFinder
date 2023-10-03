package com.example.salefinder;

import android.app.Application;

import androidx.room.Room;

import com.example.salefinder.database.AppDatabase;

public class MainApplication extends Application {
    AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        // when upgrading versions, kill the original tables by using fallbackToDestructiveMigration()
        appDatabase = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.NAME).fallbackToDestructiveMigration().build();
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
