package com.ucicke.mygallery.di.components;

import com.ucicke.mygallery.di.module.PhotoModule;
import com.ucicke.mygallery.di.scope.PerActivity;
import com.ucicke.mygallery.modules.favorite_list.FavoriteActivity;
import com.ucicke.mygallery.modules.recently_list.GalleryActivity;

import dagger.Component;

@PerActivity
@Component(modules = PhotoModule.class, dependencies = ApplicationComponent.class)
public interface PhotoComponent {

    void inject(GalleryActivity activity);
    void inject(FavoriteActivity activity);
}
