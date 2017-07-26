package com.ucicke.mygallery.mapper;

import com.ucicke.mygallery.mvp.model.Data;
import com.ucicke.mygallery.mvp.model.Photo;
import com.ucicke.mygallery.mvp.model.Photos;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PhotoMapper {

    @Inject
    PhotoMapper() {

    }

    public List<Photo> mapPhotos(Data response) {
        List<Photo> photoList = new ArrayList<>();

        if (response != null) {
            Photos photos = response.getPhotos();
            if (photos != null) {
                photoList.addAll(photos.getPhotoList());
            }
        }

        return photoList;
    }
}
