package com.ucicke.mygallery.di.module;

import com.ucicke.mygallery.di.scope.PerActivity;
import com.ucicke.mygallery.mvp.view.MainView;
import com.ucicke.mygallery.api.FlickrApiService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class PhotoModule {

    private MainView mView;

    public PhotoModule(MainView view) {
        mView = view;
    }

    @PerActivity
    @Provides
    FlickrApiService provideFlickrApiService(Retrofit retrofit) {
        return retrofit.create(FlickrApiService.class);
    }

    @PerActivity
    @Provides
    MainView provideView() {
        return mView;
    }
}
