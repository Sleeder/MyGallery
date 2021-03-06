package com.ucicke.mygallery.modules.recently_list.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.ucicke.mygallery.R;
import com.ucicke.mygallery.modules.details.DetailActivity;
import com.ucicke.mygallery.mvp.model.Photo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {
    private List<Photo> mPhotoItems = new ArrayList<>();

    private int position;
    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item, parent, false);
        return new PhotoHolder(v);
    }

    @Override
    public void onBindViewHolder(final PhotoHolder holder, int position) {
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(holder.getPosition());
                return false;
            }
        });
        holder.bindGalleryItem(mPhotoItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mPhotoItems.size();
    }

    public Photo getPhoto(int position) {
        return mPhotoItems.get(position);
    }

    public void addPhotos(List<Photo> photos) {
        mPhotoItems.clear();
        mPhotoItems.addAll(photos);
        notifyDataSetChanged();
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    class PhotoHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {

        @BindView(R.id.photo_gallery_image_view)
        ImageView mItemImageView;
        Context context;
        Photo mGalleryItem;

        PhotoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
            context = itemView.getContext();
        }

        void bindGalleryItem(Photo photo) {
            mGalleryItem = photo;
            Picasso.with(context)
                    .load(photo.getUrl())
                    .into(mItemImageView);
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent i = DetailActivity
                    .newIntent(context, mGalleryItem.getPhotoPageUri());
            context.startActivity(i);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle(R.string.select_action);
            contextMenu.add(Menu.NONE, 1,
                    Menu.NONE, R.string.add_favorite);
        }

    }
}
