package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseActivity extends SQLiteOpenHelper {

    private static String DataBase_Name = "Test";
    private static int DataBase_Version = 1;

    public DataBaseActivity(@Nullable Context context) {
        super(context, DataBase_Name, null, DataBase_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_GUESTS_TABLE = "CREATE TABLE " +  "Test_Table"  +" ("
                + Constants.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Constants.NAME_TASK + " varchar(200), " +
                Constants.BODY_TASK + " text," +
                Constants.DATE_ADDED + " text," +
                Constants.DATE_COMPLETED + " text);";
        db.execSQL(SQL_CREATE_GUESTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
