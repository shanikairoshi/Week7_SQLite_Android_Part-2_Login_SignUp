package com.example.week7.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.week7.model.User;
import com.example.week7.util.Util;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Create table. It is good practice to use capital letter for SQL queries.

        String CREATE_USER_TABLE = "CREATE TABLE" + Util.TABLE_NAME + "(" + Util.USER_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Util.USERNAME + "TEXT" + Util.PASSWORD + "TEXT)";

        db.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_USER_TABLE = "DROP TABLE IF EXISTS";
        db.execSQL(DROP_USER_TABLE, new String[]{Util.TABLE_NAME});

        //when drop the table need to create a db again.
        onCreate(db);

    }

    public long insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Util.USERNAME, user.getUsername());
        contentValues.put(Util.PASSWORD, user.getPassword());

        long newRowId = db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
        return newRowId;
    }
    public boolean fetchUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                Util.TABLE_NAME,
                new String[] { Util.USER_ID },
                Util.USERNAME + "=? and " + Util.PASSWORD + "=?",
                new String[] { username, password },
                null,
                null,
                null
        );
        int numberOfRows = cursor.getCount();
        db.close();

        if (numberOfRows > 0) {
            return true;
        } else {
            return false;
        }
    }

}