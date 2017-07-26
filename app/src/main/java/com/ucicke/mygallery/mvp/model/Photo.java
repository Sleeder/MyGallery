package com.ucicke.mygallery.mvp.model;

import android.net.Uri;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("id")
    @Expose
    private String mId;
    @SerializedName("owner")
    @Expose
    private String mOwner;
    @SerializedName("secret")
    @Expose
    private String mSecret;
    @SerializedName("server")
    @Expose
    private String mServer;
    @SerializedName("farm")
    @Expose
    private Integer mFarm;
    @SerializedName("title")
    @Expose
    private String mTitle;
    @SerializedName("ispublic")
    @Expose
    private Integer mIspublic;
    @SerializedName("isfriend")
    @Expose
    private Integer mIsfriend;
    @SerializedName("isfamily")
    @Expose
    private Integer mIsfamily;
    @SerializedName("url_s")
    @Expose
    private String mUrl;
    @SerializedName("height_s")
    @Expose
    private String mHeight;
    @SerializedName("width_s")
    @Expose
    private String mWidth;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getOwner() {
        return mOwner;
    }

    public void setOwner(String owner) {
        this.mOwner = owner;
    }

    public String getSecret() {
        return mSecret;
    }

    public void setSecret(String secret) {
        this.mSecret = secret;
    }

    public String getServer() {
        return mServer;
    }

    public void setServer(String server) {
        this.mServer = server;
    }

    public Integer getFarm() {
        return mFarm;
    }

    public void setFarm(Integer farm) {
        this.mFarm = farm;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public Integer getIspublic() {
        return mIspublic;
    }

    public void setIspublic(Integer ispublic) {
        this.mIspublic = ispublic;
    }

    public Integer getIsfriend() {
        return mIsfriend;
    }

    public void setIsfriend(Integer isfriend) {
        this.mIsfriend = isfriend;
    }

    public Integer getIsfamily() {
        return mIsfamily;
    }

    public void setIsfamily(Integer isfamily) {
        this.mIsfamily = isfamily;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public String getHeight() {
        return mHeight;
    }

    public void setHeight(String height) {
        this.mHeight = height;
    }

    public String getWidth() {
        return mWidth;
    }

    public void setWidth(String width) {
        this.mWidth = width;
    }

    public Uri getPhotoPageUri() {
        return Uri.parse("http://www.flickr.com/photos/")
                .buildUpon()
                .appendPath(mOwner)
                .appendPath(mId)
                .build();
    }
}