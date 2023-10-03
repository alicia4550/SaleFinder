package com.example.salefinder.component;

import android.app.Application;

import com.example.salefinder.FirstFragment;
import com.example.salefinder.MainActivity;
import com.example.salefinder.SecondFragment;
import com.example.salefinder.module.AppModule;
import com.example.salefinder.module.DatabaseModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, DatabaseModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
    void inject(FirstFragment firstFragment);
    void inject(SecondFragment secondFragment);
    Application application();
}
