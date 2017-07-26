package com.ucicke.mygallery.modules.favorite_list;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewTreeObserver;

import com.ucicke.mygallery.R;
import com.ucicke.mygallery.base.BaseActivity;
import com.ucicke.mygallery.di.components.DaggerPhotoComponent;
import com.ucicke.mygallery.di.module.PhotoModule;
import com.ucicke.mygallery.modules.favorite_list.adapter.FavoriteAdapter;
import com.ucicke.mygallery.mvp.model.Photo;
import com.ucicke.mygallery.mvp.model.Storage;
import com.ucicke.mygallery.mvp.view.MainView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class FavoriteActivity extends BaseActivity implements MainView {

    @BindView(R.id.photo_gallery_recycler_view)
    RecyclerView mPhotoRecyclerView;

    @Inject
    Storage mStorage;

    private FavoriteAdapter mAdapter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        initializeRecyclerView();
        List<Photo> savedPhotos = mStorage.getSavedPhotos();
        mAdapter.addPhotos(savedPhotos);
    }

    private void initializeRecyclerView() {
        mPhotoRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int numOfCol = mPhotoRecyclerView.getWidth() / 320;
                GridLayoutManager gridLayoutManager = new GridLayoutManager(FavoriteActivity.this, numOfCol);
                mPhotoRecyclerView.setLayoutManager(gridLayoutManager);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mPhotoRecyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    mPhotoRecyclerView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });

        mAdapter = new FavoriteAdapter();
        mPhotoRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_gallery;
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerPhotoComponent.builder()
                .applicationComponent(getApplicationComponent())
                .photoModule(new PhotoModule(this))
                .build().inject(this);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, FavoriteActivity.class);
    }

    @Override
    public void onPhotosLoaded(List<Photo> photos) {

    }

    @Override
    public void onShowDialog(String message) {

    }

    @Override
    public void onHideDialog() {

    }

    @Override
    public void onShowToast(String message) {

    }
}
