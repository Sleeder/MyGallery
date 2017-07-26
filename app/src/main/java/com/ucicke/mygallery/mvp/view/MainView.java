package com.ucicke.mygallery.mvp.view;

import com.ucicke.mygallery.mvp.model.Photo;

import java.util.List;

public interface MainView extends BaseView {
    void onPhotosLoaded(List<Photo> photos);

    void onShowDialog(String message);

    void onHideDialog();

    void onShowToast(String message);
}
