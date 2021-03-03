package com.example.pdms;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Admin_Controller extends SQLiteOpenHelper {


    public Admin_Controller(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "PatientInfo.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PATIENTS(ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT UNIQUE, PASSWORD TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS PATIENTS;");
        onCreate(db);
    }
    public void insert_users(String username, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username",username);
        contentValues.put("Password", password);
        this.getWritableDatabase().insertOrThrow("PATIENTS", "", contentValues);
    }
    public void delete_users(String username) {
        this.getWritableDatabase().delete("PATIENTS", "USERNAME='"+username+"'",null);
    }

    public void list_all_users(TextView textView) {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM PATIENTS", null);
        textView.setText("");
        while(cursor.moveToNext()) {
            textView.append(cursor.getString(1)+" "+cursor.getString(2)+"\n");
        }
    }
}


