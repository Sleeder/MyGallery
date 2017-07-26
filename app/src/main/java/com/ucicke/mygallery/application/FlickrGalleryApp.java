package com.ucicke.mygallery.application;

import android.app.Application;

import com.ucicke.mygallery.di.components.ApplicationComponent;
import com.ucicke.mygallery.di.components.DaggerApplicationComponent;
import com.ucicke.mygallery.di.module.ApplicationModule;

import static com.ucicke.mygallery.consts.FlickrURL.BASE_URL;

public class FlickrGalleryApp extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(BASE_URL, this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
