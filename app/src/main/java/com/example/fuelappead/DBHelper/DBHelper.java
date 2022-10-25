package com.example.fuelappead.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "fuelapp.db";

    public DBHelper(Context context) {
        super(context, "fuelapp.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table users(username TEXT, phonenumber TEXT primary key, role TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists users");
    }

    public Boolean RegisterUser(String username, String phonenumber,String role, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", username);
        values.put("phonenumber", phonenumber);
        values.put("role", role);
        values.put("password", password);

        long result = db.insert("users", null, values);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Boolean usernameCheck(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=?", new String[] {username});

        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean usernamepassCheck(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where phonenumber=? and password=?", new String[] {username,password});

        if (cursor.getCount()> 0)
            return true;
        else
            return false;
    }
    public Boolean userRoleCheck(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where phonenumber=? and password=? and role='Shed Owner'", new String[] {username,password});

        if (cursor.getCount()> 0)
            return true;
        else
            return false;
    }
}
