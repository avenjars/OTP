package com.example.lenovo.otp;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class LoginDB extends SQLiteOpenHelper {
    public LoginDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table login(mobile_no text,date text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table login");
    }

    public void doLogin(String number) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "insert into login values('" + number + "','" + new Date().toString() + "')";
        db.execSQL(sql);
    }

    public void logout() {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "truncate table login";
        db.execSQL(sql);
    }

    public String chkLogin() {
        SQLiteDatabase db = getReadableDatabase();
        String s = null;
        Cursor c = db.rawQuery("select mobile_no from login", null);
        while (c.moveToNext()) {
            s = c.getString(0);
        }
        if(s!=null)
        return s;
        else
            return  "no";
    }

}
