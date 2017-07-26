package com.ucicke.mygallery.api;

import com.ucicke.mygallery.mvp.model.Data;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

import static com.ucicke.mygallery.consts.FlickrURL.APIKEY_SEARCH_STRING;
import static com.ucicke.mygallery.consts.FlickrURL.EXTRAS;
import static com.ucicke.mygallery.consts.FlickrURL.FORMAT_JSON;
import static com.ucicke.mygallery.consts.FlickrURL.JSON_CALLBACK;
import static com.ucicke.mygallery.consts.FlickrURL.METHOD_PREFIX;
import static com.ucicke.mygallery.consts.FlickrURL.PHOTO_SEARCH;
import static com.ucicke.mygallery.consts.FlickrURL.RECENT_PHOTOS;

public interface FlickrApiService {

    @GET(METHOD_PREFIX + RECENT_PHOTOS + FORMAT_JSON + JSON_CALLBACK + APIKEY_SEARCH_STRING + EXTRAS)
    Observable<Data> getRecentPhotos();

    @GET(METHOD_PREFIX + PHOTO_SEARCH + FORMAT_JSON + JSON_CALLBACK + APIKEY_SEARCH_STRING + EXTRAS)
    Observable<Data> getFoundPhotos(@Query("text") String queryText);
}
