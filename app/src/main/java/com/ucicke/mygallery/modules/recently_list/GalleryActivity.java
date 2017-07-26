package com.ucicke.mygallery.modules.recently_list;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.ucicke.mygallery.R;
import com.ucicke.mygallery.base.BaseActivity;
import com.ucicke.mygallery.di.components.DaggerPhotoComponent;
import com.ucicke.mygallery.di.module.PhotoModule;
import com.ucicke.mygallery.modules.recently_list.adapter.PhotoAdapter;
import com.ucicke.mygallery.mvp.model.Photo;
import com.ucicke.mygallery.mvp.model.Storage;
import com.ucicke.mygallery.mvp.presenter.PhotoPresenter;
import com.ucicke.mygallery.mvp.view.MainView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class GalleryActivity extends BaseActivity implements MainView {

    @BindView(R.id.photo_gallery_recycler_view)
    RecyclerView mPhotoRecyclerView;

    @Inject
    protected PhotoPresenter mPhotoPresenter;
    @Inject
    Storage mStorage;

    private PhotoAdapter mAdapter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        registerForContextMenu(mPhotoRecyclerView);
        initializeRecyclerView();
        mPhotoPresenter.getPhotos(null);
    }

    private void initializeRecyclerView() {
        mPhotoRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int numOfCol = mPhotoRecyclerView.getWidth() / 320;
                GridLayoutManager gridLayoutManager = new GridLayoutManager(GalleryActivity.this, numOfCol);
                mPhotoRecyclerView.setLayoutManager(gridLayoutManager);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mPhotoRecyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    mPhotoRecyclerView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });

        mAdapter = new PhotoAdapter();
        mPhotoRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_gallery;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.photo_gallery_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_item_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener
                (new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        mPhotoPresenter.getPhotos(s);
                        mPhotoRecyclerView.scrollToPosition(0);
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        return false;
                    }
                });
        return true;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position = -1;
        try {
            position = mAdapter.getPosition();
        } catch (Exception e) {
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) {
            case 1:
                Photo photo = mAdapter.getPhoto(position);
                mStorage.addPhoto(photo);
                break;
        }
        return super.onContextItemSelected(item);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, GalleryActivity.class);
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerPhotoComponent.builder()
                .applicationComponent(getApplicationComponent())
                .photoModule(new PhotoModule(this))
                .build().inject(this);
    }

    @Override
    public void onPhotosLoaded(List<Photo> photos) {
        mAdapter.addPhotos(photos);
    }

    @Override
    public void onShowDialog(String message) {
        showDialog(message);
    }

    @Override
    public void onHideDialog() {
        hideDialog();
    }

    @Override
    public void onShowToast(String message) {
        Toast.makeText(GalleryActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
