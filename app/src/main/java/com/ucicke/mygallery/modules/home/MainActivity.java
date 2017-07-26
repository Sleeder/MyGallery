package com.ucicke.mygallery.modules.home;

import com.ucicke.mygallery.R;
import com.ucicke.mygallery.base.BaseActivity;
import com.ucicke.mygallery.modules.favorite_list.FavoriteActivity;
import com.ucicke.mygallery.modules.recently_list.GalleryActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @OnClick(R.id.recently_button)
    public void startGallery() {
        startActivity(GalleryActivity.newIntent(this));
    }

    @OnClick(R.id.favorites_button)
    public void startFavorites() {
        startActivity(FavoriteActivity.newIntent(this));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }
}
