package com.android.class2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

public class DBOperator extends SQLiteOpenHelper{

    private static final int VERSION = 1;
    private static final String DB_NAME = "class2.db";

    public DBOperator(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder builder = new StringBuilder();
        builder.append("Create table ").append("items").append("(")
                .append("id").append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append("description").append(" TEXT, ")
                .append("full_image_path").append(" TEXT , ")
                .append("thumbnail_image_path").append(" TEXT")
                .append(");");
        db.execSQL(builder.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long saveItem(String description, String fullImagePath, String thumbnailImagePath) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("description", description);
        contentValues.put("full_image_path", fullImagePath);
        contentValues.put("thumbnail_image_path", thumbnailImagePath);

        SQLiteDatabase writableDatabase = getWritableDatabase();
        long result = writableDatabase.insert("items", null, contentValues);
        writableDatabase.close();
        return result;
    }
}
