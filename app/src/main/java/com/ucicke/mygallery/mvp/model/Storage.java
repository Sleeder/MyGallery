package com.ucicke.mygallery.mvp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class Storage extends SQLiteOpenHelper {

    private static final String TAG = Storage.class.getSimpleName();

    @Inject
    public Storage(Context context) {
        super(context, "photos_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
        } catch (SQLException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void addPhoto(Photo photo) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(PHOTO_ID, photo.getId());
        cv.put(OWNER, photo.getOwner());
        cv.put(SECRET, photo.getSecret());
        cv.put(SERVER, photo.getServer());
        cv.put(FARM, photo.getFarm());
        cv.put(TITLE, photo.getTitle());
        cv.put(ISPUBLIC, photo.getIspublic());
        cv.put(ISFRIEND, photo.getIsfriend());
        cv.put(ISFAMILY, photo.getIsfamily());
        cv.put(URL, photo.getUrl());
        cv.put(HEIGHT, photo.getHeight());
        cv.put(WIDTH, photo.getWidth());

        try {
            db.insert(TABLE_NAME, null, cv);
        } catch (SQLException e) {
            Log.d(TAG, e.getMessage());
        }

        db.close();
    }

    public List<Photo> getSavedPhotos() {
        List<Photo> photoList = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery(SELECT_QUERY, null);
        if (cursor != null) {
            if(cursor.getCount() > 0) {
                if(cursor.moveToFirst()) {
                    do {
                        Photo photo = new Photo();
                        photo.setId(cursor.getString(cursor.getColumnIndex(PHOTO_ID)));
                        photo.setOwner(cursor.getString(cursor.getColumnIndex(OWNER)));
                        photo.setSecret(cursor.getString(cursor.getColumnIndex(SECRET)));
                        photo.setServer(cursor.getString(cursor.getColumnIndex(SERVER)));
                        photo.setFarm(cursor.getInt(cursor.getColumnIndex(FARM)));
                        photo.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
                        photo.setIspublic(cursor.getInt(cursor.getColumnIndex(ISPUBLIC)));
                        photo.setIsfriend(cursor.getInt(cursor.getColumnIndex(ISFRIEND)));
                        photo.setIsfamily(cursor.getInt(cursor.getColumnIndex(ISFAMILY)));
                        photo.setUrl(cursor.getString(cursor.getColumnIndex(URL)));
                        photo.setHeight(cursor.getString(cursor.getColumnIndex(HEIGHT)));
                        photo.setWidth(cursor.getString(cursor.getColumnIndex(WIDTH)));

                        photoList.add(photo);
                    } while (cursor.moveToNext());
                }
            }
        }
        db.close();

        return photoList;
    }

    public void deleteAll() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }

    private static String TABLE_NAME = "photos";
    private static String ID = "id";
    private static String PHOTO_ID = "photoId";
    private static String OWNER = "owner";
    private static String SECRET = "secret";
    private static String SERVER = "server";
    private static String FARM = "farm";
    private static String TITLE = "title";
    private static String ISPUBLIC = "ispublic";
    private static String ISFRIEND = "isfriend";
    private static String ISFAMILY = "isfamily";
    private static String URL = "url";
    private static String HEIGHT = "height";
    private static String WIDTH = "width";

    private static String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static String SELECT_QUERY = "SELECT * FROM " + TABLE_NAME;

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            PHOTO_ID + " TEXT," +
            OWNER + " TEXT," +
            SECRET + " TEXT," +
            SERVER + " TEXT," +
            FARM + " INTEGER," +
            TITLE + " TEXT," +
            ISPUBLIC + " INTEGER," +
            ISFRIEND + " INTEGER," +
            ISFAMILY + " INTEGER," +
            URL + " TEXT," +
            HEIGHT + " TEXT," +
            WIDTH + " TEXT)";
}
