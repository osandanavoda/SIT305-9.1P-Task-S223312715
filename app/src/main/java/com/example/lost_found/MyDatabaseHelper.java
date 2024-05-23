package com.example.lost_found;

import android.content.Context;
import androidx.annotation.Nullable;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final int VERSION=1;
    public static final String MyDB="mydatabase.db";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, MyDB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE MyTable (id INTEGER PRIMARY KEY, PostType TEXT, Name TEXT, Phone TEXT, Description TEXT, Date TEXT, Location TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS MyTable");
    }

    public Cursor getdata()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM MyTable",null);
        return cursor;
    }

    public List<String> getColumnValues(String tableName, String columnName) {
        List<String> columnValues = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(tableName, new String[]{columnName}, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String value = cursor.getString(cursor.getColumnIndex(columnName));
                columnValues.add(value);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return columnValues;
    }




}
