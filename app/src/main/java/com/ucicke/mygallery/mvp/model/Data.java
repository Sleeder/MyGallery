package com.ucicke.mygallery.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("photos")
    @Expose
    private Photos mPhotos;
    @SerializedName("stat")
    @Expose
    private String mStat;

    public Photos getPhotos() {
        return mPhotos;
    }

    public void setPhotos(Photos photos) {
        this.mPhotos = photos;
    }

    public String getStat() {
        return mStat;
    }

    public void setStat(String stat) {
        this.mStat = stat;
    }

}