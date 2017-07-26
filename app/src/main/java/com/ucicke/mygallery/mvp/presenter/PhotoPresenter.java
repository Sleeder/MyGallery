package com.ucicke.mygallery.mvp.presenter;

import android.util.Log;

import com.ucicke.mygallery.api.FlickrApiService;
import com.ucicke.mygallery.base.BasePresenter;
import com.ucicke.mygallery.mapper.PhotoMapper;
import com.ucicke.mygallery.mvp.model.Data;
import com.ucicke.mygallery.mvp.model.Photo;
import com.ucicke.mygallery.mvp.view.MainView;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;

public class PhotoPresenter extends BasePresenter<MainView> implements Observer<Data> {
    private static final String TAG = PhotoPresenter.class.getSimpleName();

    @Inject
    FlickrApiService mApiService;
    @Inject
    PhotoMapper mPhotoMapper;

    @Inject
    PhotoPresenter() {
    }

    public void getPhotos(String query) {
        Observable<Data> photosResponseObservable;
        if (query != null) {
            getView().onShowDialog("Searching photos...");
            photosResponseObservable = mApiService.getFoundPhotos(query);
        } else {
            getView().onShowDialog("Loading recent photos...");
            photosResponseObservable = mApiService.getRecentPhotos();
        }

        subscribe(photosResponseObservable, this);
    }

    @Override
    public void onCompleted() {
        getView().onHideDialog();
        getView().onShowToast("Photos loaded!");
    }

    @Override
    public void onError(Throwable e) {
        getView().onHideDialog();
        getView().onShowToast("Error loading photos");
    }

    @Override
    public void onNext(Data data) {
        List<Photo> photos = mPhotoMapper.mapPhotos(data);
        Log.d(TAG, "onNext: " + photos.get(0).getId());
        getView().onPhotosLoaded(photos);

    }
}
