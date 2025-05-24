package com.example.uts_pemrograman4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME    = "UserDB";
    private static final int    DB_VERSION = 1;
    private static final String TABLE_USER = "users";
    private static final String COL_ID      = "id";
    private static final String COL_EMAIL   = "email";
    private static final String COL_PASS    = "password";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_USER + " ("
                + COL_ID    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_EMAIL + " TEXT UNIQUE, "
                + COL_PASS  + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    // Insert user baru
    public boolean insertUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_EMAIL, email);
        cv.put(COL_PASS,  password);
        long result = db.insert(TABLE_USER, null, cv);
        return result != -1;
    }

    // Cek login
    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_USER,
                new String[]{COL_ID},
                COL_EMAIL + "=? AND " + COL_PASS + "=?",
                new String[]{email, password},
                null, null, null
        );
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
}
