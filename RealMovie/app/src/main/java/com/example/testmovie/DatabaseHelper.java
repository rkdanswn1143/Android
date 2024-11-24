package com.example.testmovie;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "movie.db";
    private static final int DATABASE_VERSION = 5; // 버전을 5로 업데이트

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS movies (id INTEGER PRIMARY KEY AUTOINCREMENT, movie_id TEXT, title TEXT, original_title TEXT, poster_path TEXT, overview TEXT, release_date TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS reviews (id INTEGER PRIMARY KEY AUTOINCREMENT, movie_id TEXT, review TEXT, username TEXT, name TEXT, likes INTEGER DEFAULT 0, dislikes INTEGER DEFAULT 0)"); // 추천 및 비추천 컬럼 추가
        db.execSQL("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, email TEXT, password TEXT, name TEXT, isAdmin INTEGER DEFAULT 0)"); // isAdmin 컬럼 추가
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 5) {
            db.execSQL("ALTER TABLE users ADD COLUMN isAdmin INTEGER DEFAULT 0");
        }
    }
}