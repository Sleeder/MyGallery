package com.ucicke.mygallery.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photos {

    @SerializedName("page")
    @Expose
    private Integer mPage;
    @SerializedName("pages")
    @Expose
    private Integer mPages;
    @SerializedName("perpage")
    @Expose
    private Integer mPerpage;
    @SerializedName("total")
    @Expose
    private Integer mTotal;
    @SerializedName("photo")
    @Expose
    private List<Photo> mPhotoList = null;

    public Integer getPage() {
        return mPage;
    }

    public void setPage(Integer page) {
        this.mPage = page;
    }

    public Integer getPages() {
        return mPages;
    }

    public void setPages(Integer pages) {
        this.mPages = pages;
    }

    public Integer getPerpage() {
        return mPerpage;
    }

    public void setPerpage(Integer perpage) {
        this.mPerpage = perpage;
    }

    public Integer getTotal() {
        return mTotal;
    }

    public void setTotal(Integer total) {
        this.mTotal = total;
    }

    public List<Photo> getPhotoList() {
        return mPhotoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.mPhotoList = photoList;
    }

}